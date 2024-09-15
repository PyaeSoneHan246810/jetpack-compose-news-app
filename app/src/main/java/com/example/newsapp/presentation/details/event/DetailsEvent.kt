package com.example.newsapp.presentation.details.event

sealed class DetailsEvent {
    data object BookmarkArticle: DetailsEvent()
}