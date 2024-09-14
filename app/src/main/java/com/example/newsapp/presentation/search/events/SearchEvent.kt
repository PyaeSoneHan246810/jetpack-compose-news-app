package com.example.newsapp.presentation.search.events

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    data object SearchArticles: SearchEvent()
}