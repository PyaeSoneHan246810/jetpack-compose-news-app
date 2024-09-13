package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.ui.theme.ARTICLE_CARD_SIZE
import com.example.newsapp.ui.theme.CORNER_XS
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_XS

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(CORNER_XS))
            .clickable(
                onClick = onClick
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ARTICLE_CARD_SIZE)
                .clip(RoundedCornerShape(CORNER_XS)),
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
        Column(
            modifier = Modifier
                .weight(1f)
                .height(ARTICLE_CARD_SIZE)
                .padding(horizontal = SPACING_XS),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ArticleCardPrev() {
    NewsAppTheme {
        Surface {
            ArticleCard(
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
                onClick = {}
            )
        }
    }
}