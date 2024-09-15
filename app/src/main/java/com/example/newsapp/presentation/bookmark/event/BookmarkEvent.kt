package com.example.newsapp.presentation.bookmark.event

import com.example.newsapp.domain.model.Article

sealed class BookmarkEvent {
    data class InsertBookmarkArticle(val article: Article): BookmarkEvent()
    data class DeleteBookmarkArticles(val article: Article): BookmarkEvent()
}