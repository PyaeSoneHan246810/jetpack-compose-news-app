package com.example.newsapp.presentation.newsNavigator.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.bookmark.screen.BookmarkScreen
import com.example.newsapp.presentation.bookmark.viewModel.BookmarkViewModel
import com.example.newsapp.presentation.details.event.DetailsEvent
import com.example.newsapp.presentation.details.screen.DetailsScreen
import com.example.newsapp.presentation.details.viewModel.DetailsViewModel
import com.example.newsapp.presentation.home.screen.HomeScreen
import com.example.newsapp.presentation.home.viewModel.HomeViewModel
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.newsNavigator.component.BottomNavigationBar
import com.example.newsapp.presentation.newsNavigator.uiData.navigationBarItems
import com.example.newsapp.presentation.search.screen.SearchScreen
import com.example.newsapp.presentation.search.viewModel.SearchViewModel
import com.example.newsapp.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigatorScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    var selectedNavigationBarItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val isBottomBarVisible by remember {
        derivedStateOf {
            (currentBackStackEntry?.destination?.route == Screen.HomeScreen.route ||
                    currentBackStackEntry?.destination?.route == Screen.SearchScreen.route ||
                    currentBackStackEntry?.destination?.route == Screen.BookmarksScreen.route)
        }
    }
    selectedNavigationBarItemIndex = remember {
        derivedStateOf {
            when(currentBackStackEntry?.destination?.route) {
                Screen.HomeScreen.route -> 0
                Screen.SearchScreen.route -> 1
                Screen.BookmarksScreen.route -> 2
                else -> 0
            }
        }
    }.value
    val bottomAppBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(bottomAppBarScrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationBar(
                    scrollBehavior = bottomAppBarScrollBehavior,
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
                        articles = viewModel.articles!!.collectAsLazyPagingItems(),
                        onNavigateToSearchScreen = { route ->
                            navigateToScreen(navController, route)
                        },
                        onNavigateToDetailsScreen = { route, article  ->
                            navigateToDetailsScreen(navController, article, route)
                        },
                        isArticlesListRefreshing = viewModel.isRefreshing,
                        onArticlesListRefresh = viewModel::onEvent
                    )
                }
                composable(Screen.SearchScreen.route) {
                    val viewModel: SearchViewModel = hiltViewModel()
                    SearchScreen(
                        searchState = viewModel.searchState,
                        onSearchQueryUpdated = viewModel::onEvent,
                        onSearchArticles = viewModel::onEvent,
                        onNavigateToDetailsScreen = { route, article ->
                            navigateToDetailsScreen(navController, article, route)
                        },
                        isArticlesListRefreshing = viewModel.isRefreshing,
                        onArticlesListRefresh = viewModel::onEvent
                    )
                }
                composable(Screen.BookmarksScreen.route) {
                    val viewModel: BookmarkViewModel = hiltViewModel()
                    val context = LocalContext.current
                    BookmarkScreen(
                        articles = viewModel.articles,
                        onSwipeToDelete = { event ->
                            viewModel.onEvent(event)
                            Toast.makeText(context, "Successfully removed the article!", Toast.LENGTH_SHORT).show()
                        },
                        onNavigateToDetailsScreen = { route, article ->
                            navigateToDetailsScreen(navController, article, route)
                        }
                    )
                }
                composable(Screen.DetailsScreen.route) {
                    val viewModel: DetailsViewModel = hiltViewModel()
                    val context = LocalContext.current
                    val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>(Constants.ARTICLE_KEY)
                    LaunchedEffect(key1 = viewModel.message) {
                        viewModel.message?.let { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            viewModel.onEvent(DetailsEvent.RemoveMessage)
                        }
                    }
                    article?.let {
                        viewModel.onEvent(DetailsEvent.GetBookmarkArticle(it.url))
                        DetailsScreen(
                            article = it,
                            isAlreadyBookmarked = viewModel.isAlreadyBookmarked(),
                            onNavigateBack = {
                                navController.navigateUp()
                            },
                            onBookmarkClick = viewModel::onEvent
                        )
                    }
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

private fun navigateToDetailsScreen(navController: NavHostController, article: Article, route: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set(Constants.ARTICLE_KEY, article)
    navController.navigate(route)
}