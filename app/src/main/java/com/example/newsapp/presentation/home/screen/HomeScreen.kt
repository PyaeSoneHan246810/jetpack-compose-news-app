package com.example.newsapp.presentation.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.ArticlesContent
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.ui.theme.SPACING_MD

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onNavigateToSearchScreen: (route: String) -> Unit,
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
            Text(
                modifier = Modifier
                    .padding(horizontal = SPACING_MD),
                text = stringResource(id = R.string.home),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(SPACING_MD))
            SearchBar(
                modifier = Modifier
                    .padding(horizontal = SPACING_MD),
                readOnly = true,
                value = "",
                onValueChange = {},
                onSearch = {},
                onClick = {
                    onNavigateToSearchScreen(Screen.SearchScreen.route)
                }
            )
            Spacer(modifier = Modifier.height(SPACING_MD))
            ArticlesContent(
                articles = articles,
                onArticleClick = { article ->
                    onNavigateToDetailsScreen(Screen.DetailsScreen.route)
                }
            )
            Spacer(modifier = Modifier.height(SPACING_MD))
        }
    }
}