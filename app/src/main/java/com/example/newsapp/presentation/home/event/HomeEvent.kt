package com.example.newsapp.presentation.home.event

sealed class HomeEvent {
    data object RefreshArticles: HomeEvent()
}