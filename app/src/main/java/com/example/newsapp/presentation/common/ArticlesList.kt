package com.example.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapp.domain.model.Article
import com.example.newsapp.ui.theme.SPACING_MD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onArticleClick: (article: Article) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize(),
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SPACING_MD),
            contentPadding = PaddingValues(SPACING_MD)
        ) {
            items(
                items = articles, key = {
                    it.url
                }
            ) { article ->
                ArticleCard(
                    article = article,
                    onClick = {
                        onArticleClick(article)
                    }
                )
            }
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onArticleClick: (article: Article) -> Unit,
    onSwipeToDelete: (article: Article) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(SPACING_MD),
        contentPadding = PaddingValues(SPACING_MD)
    ) {
        items(
            items = articles, key = {
                it.url
            }
        ) { article ->
            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
                confirmValueChange = {  value ->
                    if (value == SwipeToDismissBoxValue.EndToStart) {
                        onSwipeToDelete(article)
                        true
                    } else {
                        false
                    }
                },
                positionalThreshold = { totalDistance ->
                    totalDistance * 0.25f
                }
            )
            SwipeToDismissBox(
                state = swipeToDismissBoxState,
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true,
                backgroundContent = {
                    SwipeToDeleteItemBackground()
                }
            ) {
                ArticleCard(
                    article = article,
                    onClick = {
                        onArticleClick(article)
                    }
                )
            }
        }
    }
}