package com.gaspardeelias.rssreaderdemo.repository

import com.gaspardeelias.rssreaderdemo.repository.model.ResultWrapper
import com.gaspardeelias.rssreaderdemo.repository.model.User

class RepositoryImpl : Repository {
    override suspend fun register(user: User): ResultWrapper<User> {
        TODO("Not yet implemented")
    }
}