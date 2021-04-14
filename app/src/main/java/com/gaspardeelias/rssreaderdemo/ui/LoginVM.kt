package com.gaspardeelias.rssreaderdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaspardeelias.rssreaderdemo.repository.Repository
import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.utils.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(val repository: Repository, val prefs: Prefs) :
    ViewModel() {

    private val _uiState: MutableStateFlow<LoginStateUi> = MutableStateFlow(LoginStateUi.Loading(false))
    val uiState: StateFlow<LoginStateUi> = _uiState


    init {
        _uiState.value = LoginStateUi.Loading(false)
    }

    fun login(user: String, pass: String) {
        _uiState.value = LoginStateUi.Loading(true)
        viewModelScope.launch {
            val response = repository.login(user, pass)

            _uiState.value = LoginStateUi.Loading(false)
            when (response) {
                is ResultWrapper.Success -> {
                    prefs.token = response.value
                    _uiState.value = LoginStateUi.Success
                }
                is ResultWrapper.NetworkError -> {
                    if(response.code == 401) _uiState.value = LoginStateUi.Error("Error: Unauthorized")
                    if(response.code == 400) _uiState.value = LoginStateUi.Error("Error: Bad Request")
                }
                is ResultWrapper.GenericError -> {
                    _uiState.value = LoginStateUi.Error("Error: ${response.t}")
                }
                else -> _uiState.value = LoginStateUi.Error("Error")
            }
        }
    }

    fun register(user: String, pass: String) {
        _uiState.value = LoginStateUi.Loading(true)
        viewModelScope.launch {
            val response = repository.register(user, pass)

            _uiState.value = LoginStateUi.Loading(false)
            when (response) {
                is ResultWrapper.Success -> {
                    prefs.token = response.value
                    _uiState.value = LoginStateUi.Success
                }
                is ResultWrapper.GenericError -> LoginStateUi.Error("Error: ${_uiState.value}")
                else -> _uiState.value = LoginStateUi.Error("Error")
            }
        }
    }
}

sealed class LoginStateUi {
    object Success: LoginStateUi()
    data class Error(val msg: String): LoginStateUi()
    data class Loading(val loading: Boolean): LoginStateUi()
}