package com.example.newsapp.presentation.newsNavigator.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentation.bookmark.screen.BookmarkScreen
import com.example.newsapp.presentation.bookmark.viewModel.BookmarkViewModel
import com.example.newsapp.presentation.home.screen.HomeScreen
import com.example.newsapp.presentation.home.viewModel.HomeViewModel
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.newsNavigator.component.BottomNavigationBar
import com.example.newsapp.presentation.newsNavigator.uiData.navigationBarItems
import com.example.newsapp.presentation.search.screen.SearchScreen
import com.example.newsapp.presentation.search.viewModel.SearchViewModel

@Composable
fun NewsNavigatorScreen(
    modifier: Modifier = Modifier,
) {
    var selectedNavigationBarItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val backstackState by navController.currentBackStackEntryAsState()
    selectedNavigationBarItemIndex = when(backstackState?.destination?.route) {
        Screen.HomeScreen.route -> 0
        Screen.SearchScreen.route -> 1
        Screen.BookmarksScreen.route -> 2
        else -> 0
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            BottomNavigationBar(
                navigationBarItems = navigationBarItems,
                selectedItemIndex = selectedNavigationBarItemIndex,
                onItemClick = { clickedItemIndex ->
                    when(clickedItemIndex) {
                        0 -> navigateToScreen(navController, Screen.HomeScreen.route)
                        1 -> navigateToScreen(navController, Screen.SearchScreen.route)
                        2 -> navigateToScreen(navController, Screen.BookmarksScreen.route)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.route
            ) {
                composable(Screen.HomeScreen.route) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    HomeScreen(
                        articles = viewModel.articles.collectAsLazyPagingItems(),
                        onNavigateToSearchScreen = { route ->
                            navigateToScreen(navController, route)
                        },
                        onNavigateToDetailsScreen = { route ->

                        }
                    )
                }
                composable(Screen.SearchScreen.route) {
                    val viewModel: SearchViewModel = hiltViewModel()
                    SearchScreen(
                        searchState = viewModel.searchState,
                        onSearchQueryUpdated = viewModel::onEvent,
                        onSearchArticles = viewModel::onEvent,
                        onNavigateToDetailsScreen = { route ->

                        }
                    )
                }
                composable(Screen.BookmarksScreen.route) {
                    val viewModel: BookmarkViewModel = hiltViewModel()
                    BookmarkScreen(
                        articles = viewModel.articles,
                        onNavigateToDetailsScreen = { route ->

                        }
                    )
                }
            }
        }
    }
}

private fun navigateToScreen(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(Screen.HomeScreen.route) {
            inclusive = false
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}