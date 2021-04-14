package com.gaspardeelias.rssreaderdemo.repository

import com.gaspardeelias.rssreaderdemo.repository.model.Article
import com.gaspardeelias.rssreaderdemo.repository.model.Feed
import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.network.RssRetrofit
import com.gaspardeelias.rssreaderdemo.repository.network.dto.LoginRequest
import com.gaspardeelias.rssreaderdemo.repository.network.dto.RegisterRequest
import com.gaspardeelias.rssreaderdemo.repository.network.dto.RssSubscribeRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RepositoryImpl(
    val rssRetrofit: RssRetrofit,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override suspend fun register(user: String, password: String): ResultWrapper<String> {
        return safeApiCall(dispatcher) {
            rssRetrofit.register(RegisterRequest(user, password)).token
        }
    }

    override suspend fun login(user: String, password: String): ResultWrapper<String> {
        return safeApiCall(dispatcher) {
            rssRetrofit.login(LoginRequest(user, password)).token
        }
    }

    override suspend fun feedSubscribe(url: String): ResultWrapper<Feed> {
        return safeApiCall(dispatcher) {
            val dto = rssRetrofit.rssSubscribe(RssSubscribeRequest(url))
            Feed(dto.id, dto.title, dto.url)
        }
    }

    override suspend fun getFeeds(): ResultWrapper<List<Feed>> {
        return safeApiCall(dispatcher) {
            rssRetrofit.getFeeds()
                .map { dto -> Feed(id = dto.id, title = dto.title, url = dto.url) }
        }
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
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    var body = throwable.code()
                    ResultWrapper.NetworkError(body)
                }
                else -> {
                    ResultWrapper.GenericError(throwable)
                }
            }

        }
    }
}