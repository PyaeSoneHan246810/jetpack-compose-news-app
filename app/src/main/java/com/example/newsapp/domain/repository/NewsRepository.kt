package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getArticles(sources: List<String>): Flow<PagingData<Article>>

    fun searchArticles(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}