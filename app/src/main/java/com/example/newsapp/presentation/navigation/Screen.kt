package com.example.newsapp.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object OnBoardingScreen: Screen(route = "onBoardingScreen")
    data object NewsNavigatorScreen: Screen(route = "newsNavigatorScreen")
    data object HomeScreen: Screen(route = "homeScreen")
    data object SearchScreen: Screen(route = "searchScreen")
    data object BookmarksScreen: Screen(route = "bookmarksScreen")
    data object DetailsScreen: Screen(route = "detailsScreen")
}