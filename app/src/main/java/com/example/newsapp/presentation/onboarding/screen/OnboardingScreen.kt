package com.example.newsapp.presentation.onboarding.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.common.CustomTextButton
import com.example.newsapp.presentation.common.PrimaryButton
import com.example.newsapp.presentation.onboarding.component.OnboardingIndicators
import com.example.newsapp.presentation.onboarding.component.OnboardingPage
import com.example.newsapp.presentation.onboarding.data.Page
import com.example.newsapp.presentation.onboarding.data.pages
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.SPACING_MD
import com.example.newsapp.ui.theme.SPACING_XS
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onboardingPages: List<Page> = pages,
    onGetStarted: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = {
                    onboardingPages.size
                }
            )
            val textButtonText by remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> null
                        1 -> "Back"
                        2 -> "Back"
                        else -> null
                    }
                }
            }
            val primaryButtonText by remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> "Next"
                        1 -> "Next"
                        2 -> "Get Started"
                        else -> null
                    }
                }
            }
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                state = pagerState
            ) { index ->
                OnboardingPage(
                    page = onboardingPages[index]
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = SPACING_MD,
                        end = SPACING_MD,
                        bottom = SPACING_MD
                    )
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OnboardingIndicators(
                    pageSize = onboardingPages.size,
                    selectedPage = pagerState.currentPage
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SPACING_XS)
                ) {
                    val scope = rememberCoroutineScope()
                    textButtonText?.let {
                        CustomTextButton(
                            text = it,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage - 1
                                    )
                                }
                            }
                        )
                    }
                    primaryButtonText?.let {
                        PrimaryButton(
                            text = it,
                            onClick = {
                                if (pagerState.currentPage == 2) {
                                    onGetStarted()
                                } else {
                                    scope.launch {
                                        pagerState.animateScrollToPage(
                                            page = pagerState.currentPage + 1
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun OnboardingScreenPrev(
    modifier: Modifier = Modifier,
) {
    NewsAppTheme {
        OnboardingScreen(
            onGetStarted = {}
        )
    }
}