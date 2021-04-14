package com.gaspardeelias.rssreaderdemo.repository

import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.model.User

interface Repository {
    suspend fun register(user: User): ResultWrapper<User>
}