package com.example.newsapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.model.Article
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun ArticlesContent(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onArticleClick: (article: Article) -> Unit
) {
    //check for error at three different loading state
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    when {
        //loading content
        loadState.refresh is LoadState.Loading -> {
            ArticlesListLoadingAnimation(
                modifier = modifier
            )
        }
        //error content
        error != null -> {
            val errorMessage = when(error.error) {
                is SocketTimeoutException -> {
                    "Server Unavailable."
                }
                is ConnectException -> {
                    "Internet Unavailable."
                }
                else -> "Unknown Error Occurred."
            }
            ArticlesListErrorMessage(
                modifier = modifier,
                errorMessage = errorMessage
            )
        }
        //success content
        else -> {
            ArticlesList(
                modifier = modifier,
                articles = articles.itemSnapshotList.items,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ArticlesContent(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onArticleClick: (article: Article) -> Unit
) {
    if (articles.isEmpty()) {
        ArticlesListEmptyMessage(
            modifier = modifier,
            message = "You have not saved any articles yet!"
        )
    } else {
        ArticlesList(
            modifier = modifier,
            articles = articles,
            onArticleClick = onArticleClick
        )
    }
}