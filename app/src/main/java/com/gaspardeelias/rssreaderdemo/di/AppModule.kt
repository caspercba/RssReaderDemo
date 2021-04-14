package com.gaspardeelias.rssreaderdemo.di

import android.content.Context
import com.gaspardeelias.rssreaderdemo.repository.Repository
import com.gaspardeelias.rssreaderdemo.repository.RepositoryImpl
import com.gaspardeelias.rssreaderdemo.repository.network.RssRetrofit
import com.gaspardeelias.rssreaderdemo.utils.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = "http://167.99.162.146/"

    @Singleton
    @Provides
    fun provideOkHttpClient(prefs: Prefs): OkHttpClient {

        val authInterceptor = object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${prefs.token}")
                    .build()
                return chain.proceed(newRequest)
            }

        }

        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideRssRetrofit(retrofit: Retrofit) = retrofit.create(RssRetrofit::class.java)

    @Singleton
    @Provides
    fun provideRepository(rssRetrofit: RssRetrofit): Repository = RepositoryImpl(rssRetrofit)

    @Singleton
    @Provides
    fun providePrefs(@ApplicationContext context: Context) = Prefs(context)


}