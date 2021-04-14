package com.gaspardeelias.rssreaderdemo.repository.network.dto

data class RegisterDto(val accessToken: String)
data class LoginDto(val userId: Int, val accessToken: String)
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

data class RefreshDto(val newArticles: Int)