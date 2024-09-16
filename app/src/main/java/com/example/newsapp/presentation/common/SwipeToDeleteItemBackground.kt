package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.ui.theme.ARTICLE_CARD_SIZE
import com.example.newsapp.ui.theme.CORNER_XS
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_MD

@Composable
fun SwipeToDeleteItemBackground(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(ARTICLE_CARD_SIZE)
            .clip(RoundedCornerShape(CORNER_XS))
            .background(Color.Red),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier
                .padding(SPACING_MD),
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun SwipeToDeleteItemBackgroundPrev() {
    NewsAppTheme {
        Surface {
            SwipeToDeleteItemBackground()
        }
    }
}