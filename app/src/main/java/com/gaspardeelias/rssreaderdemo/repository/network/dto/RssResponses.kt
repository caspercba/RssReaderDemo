package com.gaspardeelias.rssreaderdemo.repository.network.dto

import com.google.gson.annotations.SerializedName

data class RegisterDto(val accessToken: String)
data class LoginDto(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("access_token") val token: String
)

data class FeedDto(val id: Int, val title: String, val url: String)
data class ArticleDto(
    val id: Int,
    val title: String,
    val url: String,
    val summary: String,
    val content: String?,
    val date: String,
    val loaded: String
)

data class ArticlesDto(val status: String, val articles: List<ArticleDto>)

data class RefreshDto(@SerializedName("new_articles")val newArticles: Int)