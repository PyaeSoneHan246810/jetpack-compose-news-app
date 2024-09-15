package com.example.newsapp.domain.usecases.articles

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.BookmarkArticlesRepository

class GetSingleBookmarkArticle(
    private val bookmarkArticlesRepository: BookmarkArticlesRepository
) {
    suspend operator fun invoke(url: String): Article? = bookmarkArticlesRepository.getSingleBookmarkArticle(url)
}