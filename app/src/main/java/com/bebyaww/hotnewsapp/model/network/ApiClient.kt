package com.bebyaww.hotnewsapp.model.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    fun provideApiServices(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor{
                val newRequest = it.request().newBuilder()
                    .addHeader("X-API-KEY", "d7cf20669e6944fb89443127c0ceed0a")
                    .build()
                it.proceed(newRequest)
            }
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

