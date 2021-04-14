package com.gaspardeelias.rssreaderdemo.repository.model

data class User(val user: String, val password: String, var token: String = "")