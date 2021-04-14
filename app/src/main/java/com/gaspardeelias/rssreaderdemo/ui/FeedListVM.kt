package com.gaspardeelias.rssreaderdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaspardeelias.rssreaderdemo.repository.Repository
import com.gaspardeelias.rssreaderdemo.repository.model.Feed
import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedListVM @Inject constructor(val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<ListStateUi> =
        MutableStateFlow(ListStateUi.Loading(false))
    val uiState: StateFlow<ListStateUi> = _uiState

    var data = MutableLiveData<List<Feed>>()

    init {
        _uiState.value = ListStateUi.Loading(false)
    }

    fun loadList() {
        viewModelScope.launch {
            _uiState.value = ListStateUi.Loading(true)
            var response = repository.getFeeds()
            when(response) {
                is ResultWrapper.Success -> data.postValue(response.value)
            }

        }
    }

}

sealed class ListStateUi {
    object Success : ListStateUi()
    data class Error(val msg: String) : ListStateUi()
    data class Loading(val loading: Boolean) : ListStateUi()
}