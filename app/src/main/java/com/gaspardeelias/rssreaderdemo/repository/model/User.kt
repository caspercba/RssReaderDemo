package com.gaspardeelias.rssreaderdemo.repository.model

data class User(val userId: Int = -1, val user: String, val password: String, var token: String = "")