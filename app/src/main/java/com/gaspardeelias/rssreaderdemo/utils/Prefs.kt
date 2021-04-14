package com.gaspardeelias.rssreaderdemo.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var token: String
        get() = preferences.getString(TOKEN, "") ?: ""
        set(value) = preferences.edit().putString(TOKEN, value).apply()

    companion object {
        private const val TOKEN = "token"
    }
}