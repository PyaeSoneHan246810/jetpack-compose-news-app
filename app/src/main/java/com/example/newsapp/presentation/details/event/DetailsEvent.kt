package com.example.newsapp.presentation.details.event

import com.example.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class GetBookmarkArticle(val url: String): DetailsEvent()
    data class BookmarkArticle(val article: Article): DetailsEvent()
    data object RemoveMessage: DetailsEvent()
}