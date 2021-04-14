package com.gaspardeelias.rssreaderdemo.repository

import com.gaspardeelias.rssreaderdemo.repository.model.Article
import com.gaspardeelias.rssreaderdemo.repository.model.Feed
import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.network.dto.FeedDto
import com.gaspardeelias.rssreaderdemo.repository.network.dto.LoginDto

interface Repository {
    suspend fun register(user: String, password: String): ResultWrapper<String>
    suspend fun login(user: String, password: String): ResultWrapper<String>
    suspend fun feedSubscribe(url: String): ResultWrapper<Feed>
    suspend fun getFeeds(): ResultWrapper<List<Feed>>
    suspend fun getArticles(feedId: Int): ResultWrapper<List<Article>>
    suspend fun refresh(feedId: Int): ResultWrapper<Int>
    suspend fun feedDelete(feedId: Int): ResultWrapper<Void>
    suspend fun articleToggleRead(feedId: Int, articleId: Int): ResultWrapper<Boolean>
}