package com.example.newsapp.presentation.bookmark

import com.example.newsapp.domain.model.Article

sealed class BookmarkEvent {
    data class RemoveBookmarkArticle(val article: Article): BookmarkEvent()
}