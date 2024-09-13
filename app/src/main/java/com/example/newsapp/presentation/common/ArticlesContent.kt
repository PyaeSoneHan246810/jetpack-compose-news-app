package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_MD
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
            LoadingShimmerAnimation(
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
            ErrorScreen(
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
fun LoadingShimmerAnimation(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(SPACING_MD),
        contentPadding = PaddingValues(SPACING_MD)
    ) {
        items(10) {
            ArticleCardWithShimmerEffect()
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onArticleClick: (article: Article) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(SPACING_MD),
        contentPadding = PaddingValues(SPACING_MD)
    ) {
        items(articles) { article ->
            ArticleCard(
                article = article,
                onClick = {
                    onArticleClick(article)
                }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun LoadingShimmerAnimationPrev() {
    NewsAppTheme {
        Surface {
            LoadingShimmerAnimation()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ErrorScreenPrev() {
    NewsAppTheme {
        Surface {
            ErrorScreen(
                errorMessage = "Unknown Error Occurred."
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ArticlesListPrev() {
    NewsAppTheme {
        Surface {
           ArticlesList(
               articles = listOf(
                   Article(
                       author = "kcloonan@insider.com (Kelly Cloonan)",
                       content = "As crypto rallies, the number of bitcoin millionaires has more than doubled in the last year. According to a new report from New World Wealth and Henley &amp; Partners, the number of bitcoin million… [+2414 chars]",
                       description = "The number of bitcoin millionaires has soared as  the crypto rallies. There have also been six new crypto billionaires minted in the past year.",
                       publishedAt = "2024-08-27T18:50:37Z",
                       source = Source(id = "business-insider", name = "Business Insider"),
                       title = "The number of bitcoin millionaires has soared 111% in the last year as the cryptocurrency rallies",
                       url = "https://markets.businessinsider.com/news/currencies/bitcoin-millionaires-crypto-rally-cryptocurrency-etf-billionaires-sec-ethereum-wealth-2024-8",
                       urlToImage = "https://www.businessinsider.com/public/assets/logos/og-image-logo-social.png?v=2023-11",
                   ),
                   Article(
                       author = "kcloonan@insider.com (Kelly Cloonan)",
                       content = "As crypto rallies, the number of bitcoin millionaires has more than doubled in the last year. According to a new report from New World Wealth and Henley &amp; Partners, the number of bitcoin million… [+2414 chars]",
                       description = "The number of bitcoin millionaires has soared as  the crypto rallies. There have also been six new crypto billionaires minted in the past year.",
                       publishedAt = "2024-08-27T18:50:37Z",
                       source = Source(id = "business-insider", name = "Business Insider"),
                       title = "The number of bitcoin millionaires has soared 111% in the last year as the cryptocurrency rallies",
                       url = "https://markets.businessinsider.com/news/currencies/bitcoin-millionaires-crypto-rally-cryptocurrency-etf-billionaires-sec-ethereum-wealth-2024-8",
                       urlToImage = "https://www.businessinsider.com/public/assets/logos/og-image-logo-social.png?v=2023-11",
                   ),
                   Article(
                       author = "kcloonan@insider.com (Kelly Cloonan)",
                       content = "As crypto rallies, the number of bitcoin millionaires has more than doubled in the last year. According to a new report from New World Wealth and Henley &amp; Partners, the number of bitcoin million… [+2414 chars]",
                       description = "The number of bitcoin millionaires has soared as  the crypto rallies. There have also been six new crypto billionaires minted in the past year.",
                       publishedAt = "2024-08-27T18:50:37Z",
                       source = Source(id = "business-insider", name = "Business Insider"),
                       title = "The number of bitcoin millionaires has soared 111% in the last year as the cryptocurrency rallies",
                       url = "https://markets.businessinsider.com/news/currencies/bitcoin-millionaires-crypto-rally-cryptocurrency-etf-billionaires-sec-ethereum-wealth-2024-8",
                       urlToImage = "https://www.businessinsider.com/public/assets/logos/og-image-logo-social.png?v=2023-11",
                   ),
                   Article(
                       author = "kcloonan@insider.com (Kelly Cloonan)",
                       content = "As crypto rallies, the number of bitcoin millionaires has more than doubled in the last year. According to a new report from New World Wealth and Henley &amp; Partners, the number of bitcoin million… [+2414 chars]",
                       description = "The number of bitcoin millionaires has soared as  the crypto rallies. There have also been six new crypto billionaires minted in the past year.",
                       publishedAt = "2024-08-27T18:50:37Z",
                       source = Source(id = "business-insider", name = "Business Insider"),
                       title = "The number of bitcoin millionaires has soared 111% in the last year as the cryptocurrency rallies",
                       url = "https://markets.businessinsider.com/news/currencies/bitcoin-millionaires-crypto-rally-cryptocurrency-etf-billionaires-sec-ethereum-wealth-2024-8",
                       urlToImage = "https://www.businessinsider.com/public/assets/logos/og-image-logo-social.png?v=2023-11",
                   )
               ),
               onArticleClick = {}
           )
        }
    }
}