package com.example.newsapp.presentation.search.event

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    data object SearchArticles: SearchEvent()

    data object RefreshArticles: SearchEvent()
}