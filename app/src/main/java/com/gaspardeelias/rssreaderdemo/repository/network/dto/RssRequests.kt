package com.gaspardeelias.rssreaderdemo.repository.network.dto

data class RegisterRequest(val user: String, val password: String)
data class LoginRequest(val user: String, val password: String)
data class RssSubscribeRequest(val url: String)