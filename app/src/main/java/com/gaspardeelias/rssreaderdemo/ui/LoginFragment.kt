package com.gaspardeelias.rssreaderdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gaspardeelias.rssreaderdemo.R
import com.gaspardeelias.rssreaderdemo.repository.network.RssRetrofit
import javax.inject.Inject

class LoginFragment: Fragment() {

    private val viewModel: LoginVM by viewModels()

    @Inject
    lateinit var rssRetrofit: RssRetrofit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.login_fragment, null)

        

        return view
    }

    override fun onResume() {
        super.onResume()

    }

}