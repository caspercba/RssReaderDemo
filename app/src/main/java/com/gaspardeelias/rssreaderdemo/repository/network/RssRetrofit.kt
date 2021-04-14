package com.gaspardeelias.rssreaderdemo.repository.network

import com.gaspardeelias.rssreaderdemo.repository.network.dto.RegisterRequest
import com.gaspardeelias.rssreaderdemo.repository.network.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RssRetrofit {

    @POST("users/register")
    suspend fun register(@Body req: RegisterRequest): RegisterResponse

}