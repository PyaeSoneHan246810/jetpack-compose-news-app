package com.example.newsapp.presentation.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.onboarding.data.Page
import com.example.newsapp.presentation.onboarding.data.pages
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_MD
import com.example.newsapp.ui.theme.SPACING_SM
import com.example.newsapp.ui.theme.SPACING_XS

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier
                .height(SPACING_MD)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SPACING_MD)
        ) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(
                modifier = Modifier
                    .height(SPACING_SM)
            )
            Text(
                text = page.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun OnboardingPagePrev() {
    NewsAppTheme {
        Surface {
            OnboardingPage(
                page = pages[0]
            )
        }
    }
}