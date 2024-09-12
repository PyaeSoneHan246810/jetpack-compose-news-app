package com.example.newsapp.presentation.navigation

sealed class Navigation(
    val route: String
) {
    data object AppStartNavigation: Navigation(route = "appStartNavigation")
    data object NewsNavigation: Navigation(route = "newsNavigation")
}