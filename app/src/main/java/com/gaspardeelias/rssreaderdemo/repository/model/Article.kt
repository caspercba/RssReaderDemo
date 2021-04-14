package com.gaspardeelias.rssreaderdemo.repository.model

data class Article(
    val id: Int,
    val title: String,
    val url: String,
    val summary: String,
    val content: String?,
    val date: String,
    val loaded: String,
    val read: Boolean = false
)