package com.example.newsapp.domain.usecases.articles

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.BookmarkArticlesRepository

class DeleteBookmarkArticle(
    private val bookmarkArticlesRepository: BookmarkArticlesRepository
) {
    suspend operator fun invoke(article: Article) {
        bookmarkArticlesRepository.deleteBookmarkArticle(article)
    }
}