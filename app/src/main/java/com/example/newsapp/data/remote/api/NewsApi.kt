package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
    ): NewsResponse

    @GET("everything")
    suspend fun searchArticles(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
    ): NewsResponse
}