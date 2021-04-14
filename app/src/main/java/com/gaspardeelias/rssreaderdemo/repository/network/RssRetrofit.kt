package com.gaspardeelias.rssreaderdemo.repository.network

import com.gaspardeelias.rssreaderdemo.repository.network.dto.*
import retrofit2.Response
import retrofit2.http.*

interface RssRetrofit {

    @POST("users/register")
    suspend fun register(@Body req: RegisterRequest): RegisterDto

    @POST("users/login")
    suspend fun login(@Body req: LoginRequest): LoginDto

    @POST("feeds/add")
    suspend fun rssSubscribe(@Body req: RssSubscribeRequest): FeedDto

    @GET("feeds")
    suspend fun getFeeds(): List<FeedDto>

    @GET("feeds/{id}/articles")
    suspend fun getArticles(@Query("id") feedId: String): ArticlesDto

    @PUT("feeds/{id}/refresh")
    suspend fun refresh(@Query("id") feedId: String): RefreshDto

    @DELETE("feeds/{id}")
    suspend fun delete(@Query("id") feedId: String): Response<Void>
}
