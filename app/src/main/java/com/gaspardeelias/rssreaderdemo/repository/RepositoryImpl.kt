package com.gaspardeelias.rssreaderdemo.repository

import com.gaspardeelias.rssreaderdemo.repository.model.Article
import com.gaspardeelias.rssreaderdemo.repository.model.Feed
import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.model.User
import com.gaspardeelias.rssreaderdemo.repository.network.RssRetrofit
import com.gaspardeelias.rssreaderdemo.repository.network.dto.LoginDto
import com.gaspardeelias.rssreaderdemo.repository.network.dto.RegisterRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(
    val rssRetrofit: RssRetrofit,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override suspend fun register(user: String, password: String): ResultWrapper<String> {
        return safeApiCall(dispatcher) {
            rssRetrofit.register(RegisterRequest(user, password)).accessToken
        }
    }

    override suspend fun login(user: String, password: String): ResultWrapper<LoginDto> {
        TODO("Not yet implemented")
    }

    override suspend fun feedSubscribe(url: String): ResultWrapper<Feed> {
        TODO("Not yet implemented")
    }

    override suspend fun getFeeds(): ResultWrapper<List<Feed>> {
        TODO("Not yet implemented")
    }

    override suspend fun getArticles(feedId: Int): ResultWrapper<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun refresh(feedId: Int): ResultWrapper<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun feedDelete(feedId: Int): ResultWrapper<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun articleToggleRead(feedId: Int, articleId: Int): ResultWrapper<Boolean> {
        TODO("Not yet implemented")
    }

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                ResultWrapper.GenericError(throwable)
            }
        }
    }
}