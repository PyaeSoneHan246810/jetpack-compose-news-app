package com.example.newsapp.presentation.bookmark.screen

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
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.ArticlesContent
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.ui.theme.SPACING_MD

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    articles: List<Article>,
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
                .padding(SPACING_MD)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = SPACING_MD),
                text = stringResource(id = R.string.bookmark_title),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(SPACING_MD))
            ArticlesContent(
                articles = articles,
                onArticleClick = { article ->
                    onNavigateToDetailsScreen(Screen.DetailsScreen.route)
                }
            )
        }
    }
}