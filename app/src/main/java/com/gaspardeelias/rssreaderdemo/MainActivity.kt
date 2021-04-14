package com.gaspardeelias.rssreaderdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaspardeelias.rssreaderdemo.ui.ListFragment
import com.gaspardeelias.rssreaderdemo.ui.LoginFragment
import com.gaspardeelias.rssreaderdemo.utils.Prefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (prefs.token.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment()).commit()
        }
    }
}