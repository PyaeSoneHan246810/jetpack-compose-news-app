package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.ui.theme.ARTICLE_CARD_SIZE
import com.example.newsapp.ui.theme.CORNER_XS
import com.example.newsapp.ui.theme.CORNER_XXS
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SHIMMER_BOX_HEIGHT
import com.example.newsapp.ui.theme.SPACING_XS

@Composable
fun ArticleCardWithShimmerEffect(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(CORNER_XS))
    ) {
        Box(
            modifier = Modifier
                .size(ARTICLE_CARD_SIZE)
                .clip(RoundedCornerShape(CORNER_XS))
                .shimmerEffect(),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .height(ARTICLE_CARD_SIZE)
                .padding(horizontal = SPACING_XS),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SHIMMER_BOX_HEIGHT)
                    .clip(RoundedCornerShape(CORNER_XXS))
                    .shimmerEffect()
            )
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SHIMMER_BOX_HEIGHT)
                        .clip(RoundedCornerShape(CORNER_XXS))
                        .weight(1f)
                        .shimmerEffect()
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun ArticleCardWithShimmerEffectPrev() {
    NewsAppTheme {
        Surface {
            ArticleCardWithShimmerEffect()
        }
    }
}