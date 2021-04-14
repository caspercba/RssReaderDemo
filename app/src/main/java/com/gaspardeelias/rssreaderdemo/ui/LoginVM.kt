package com.gaspardeelias.rssreaderdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaspardeelias.rssreaderdemo.repository.Repository
import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.network.RssRetrofit
import com.gaspardeelias.rssreaderdemo.utils.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginVM : ViewModel() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var prefs: Prefs

    fun login(user: String, pass: String) {
        viewModelScope.launch {
            val response = repository.login(user, pass)
            when(response) {
                is ResultWrapper.Success -> {
                    prefs.token = response.value
                    goNext()
                }
                else -> showError()
            }
        }
    }

    fun goNext() {

    }

    fun showError() {

    }

}