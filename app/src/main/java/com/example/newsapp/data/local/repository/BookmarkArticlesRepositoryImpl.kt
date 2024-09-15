package com.example.newsapp.data.local.repository

import com.example.newsapp.data.local.dao.BookmarkArticlesDao
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.BookmarkArticlesRepository
import kotlinx.coroutines.flow.Flow

class BookmarkArticlesRepositoryImpl(
    private val bookmarkArticlesDao: BookmarkArticlesDao
): BookmarkArticlesRepository {
    override suspend fun insertBookmarkArticle(article: Article) {
        bookmarkArticlesDao.insertBookmarkArticle(article)
    }

    override suspend fun deleteBookmarkArticle(article: Article) {
        bookmarkArticlesDao.deleteBookmarkArticle(article)
    }

    override fun getBookmarkArticles(): Flow<List<Article>> = bookmarkArticlesDao.getBookmarkArticles()
    override suspend fun getSingleBookmarkArticle(url: String): Article? = bookmarkArticlesDao.getSingleBookmarkArticle(url)
}