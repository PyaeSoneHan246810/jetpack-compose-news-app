package com.example.newsapp.presentation.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentation.common.ArticlesContent
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.search.event.SearchEvent
import com.example.newsapp.presentation.search.state.SearchState
import com.example.newsapp.ui.theme.SPACING_MD

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchState: SearchState,
    onSearchQueryUpdated: (event: SearchEvent) -> Unit,
    onSearchArticles: (event: SearchEvent) -> Unit,
    onNavigateToDetailsScreen: (route: String) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(SPACING_MD))
            SearchBar(
                modifier = Modifier
                    .padding(horizontal = SPACING_MD),
                readOnly = false,
                value = searchState.searchQuery,
                onValueChange = { newValue ->
                    onSearchQueryUpdated(
                        SearchEvent.UpdateSearchQuery(
                            searchQuery = newValue.trim()
                        )
                    )
                },
                onSearch = {
                    onSearchArticles(SearchEvent.SearchArticles)
                },
            )
            Spacer(modifier = Modifier.height(SPACING_MD))
            searchState.searchArticles?.let { searchArticlesFlow ->
                val articles = searchArticlesFlow.collectAsLazyPagingItems()
                ArticlesContent(
                    articles = articles,
                    onArticleClick = { article ->
                        onNavigateToDetailsScreen(Screen.DetailsScreen.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(SPACING_MD))
        }
    }
}