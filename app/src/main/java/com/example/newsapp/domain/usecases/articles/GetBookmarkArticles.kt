package com.example.newsapp.domain.usecases.articles

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.BookmarkArticlesRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarkArticles(
    private val bookmarkArticlesRepository: BookmarkArticlesRepository
) {
    operator fun invoke(): Flow<List<Article>> = bookmarkArticlesRepository.getBookmarkArticles()
}