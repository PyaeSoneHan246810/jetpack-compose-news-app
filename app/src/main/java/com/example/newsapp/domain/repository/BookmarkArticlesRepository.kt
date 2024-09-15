package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface BookmarkArticlesRepository {
    suspend fun insertBookmarkArticle(article: Article)

    suspend fun deleteBookmarkArticle(article: Article)

    fun getBookmarkArticles(): Flow<List<Article>>

    suspend fun getSingleBookmarkArticle(url: String): Article?
}