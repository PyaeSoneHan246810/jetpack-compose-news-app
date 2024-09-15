package com.example.newsapp.presentation.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.onboarding.uiData.pages
import com.example.newsapp.ui.theme.INDICATOR_SIZE
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_XXS

@Composable
fun OnboardingIndicators(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedItemColor: Color = MaterialTheme.colorScheme.primary,
    unselectedItemColor: Color = MaterialTheme.colorScheme.outline
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SPACING_XXS)
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(INDICATOR_SIZE)
                    .clip(CircleShape)
                    .background(if (page == selectedPage) selectedItemColor else unselectedItemColor),
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun OnboardingIndicatorsPrev() {
    NewsAppTheme {
        OnboardingIndicators(
            pageSize = pages.size,
            selectedPage = 0
        )
    }
}