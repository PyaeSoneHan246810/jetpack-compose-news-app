package com.example.newsapp.presentation.details.screen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.presentation.details.component.DetailsTopBar
import com.example.newsapp.presentation.details.event.DetailsEvent
import com.example.newsapp.ui.theme.ARTICLE_IMAGE_HEIGHT
import com.example.newsapp.ui.theme.CORNER_MD
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_LG
import com.example.newsapp.ui.theme.SPACING_MD

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    article: Article,
    onNavigateBack: () -> Unit,
    onBookmarkClick: (event: DetailsEvent) -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DetailsTopBar(
                onBackClick = onNavigateBack,
                onBookmarkClick = {
                    onBookmarkClick(DetailsEvent.BookmarkArticle)
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also { intent ->
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, "Hey! Please check this article from ${article.source.name} here ${article.url}.")
                        if (intent.resolveActivity(context.packageManager) != null) {
                            Intent.createChooser(intent, "Please choose an app to share the article.").also {
                                context.startActivity(it)
                            }
                        } else {
                            Toast.makeText(context, "Couldn't share the article!", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onBrowseClick = {
                    Intent(Intent.ACTION_VIEW, Uri.parse(article.url)).also { intent ->
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "Couldn't direct to the article link!", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            contentPadding = PaddingValues(SPACING_MD),
            verticalArrangement = Arrangement.spacedBy(SPACING_LG)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ARTICLE_IMAGE_HEIGHT)
                        .clip(RoundedCornerShape(CORNER_MD)),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(article.urlToImage)
                        .placeholder(R.drawable.article_image_placeholder)
                        .error(R.drawable.article_image_error)
                        .crossfade(true)
                        .build(),
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(SPACING_MD))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun DetailsScreenPrev() {
    NewsAppTheme {
        DetailsScreen(
            article = Article(
                author = "kcloonan@insider.com (Kelly Cloonan)",
                content = "As crypto rallies, the number of bitcoin millionaires has more than doubled in the last year. According to a new report from New World Wealth and Henley &amp; Partners, the number of bitcoin million… [+2414 chars]",
                description = "The number of bitcoin millionaires has soared as  the crypto rallies. There have also been six new crypto billionaires minted in the past year.",
                publishedAt = "2024-08-27T18:50:37Z",
                source = Source(id = "business-insider", name = "Business Insider"),
                title = "The number of bitcoin millionaires has soared 111% in the last year as the cryptocurrency rallies",
                url = "https://markets.businessinsider.com/news/currencies/bitcoin-millionaires-crypto-rally-cryptocurrency-etf-billionaires-sec-ethereum-wealth-2024-8",
                urlToImage = "https://www.businessinsider.com/public/assets/logos/og-image-logo-social.png?v=2023-11",
            ),
            onNavigateBack = {},
            onBookmarkClick = { _ ->  }
        )
    }
}