package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentation.home.screen.HomeScreen
import com.example.newsapp.presentation.home.viewModel.HomeViewModel
import com.example.newsapp.presentation.onboarding.screen.OnboardingScreen
import com.example.newsapp.presentation.onboarding.viewModel.OnBoardingViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Navigation.AppStartNavigation.route,
            startDestination = Screen.OnBoardingScreen.route
        ) {
            composable(
                route = Screen.OnBoardingScreen.route,
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnboardingScreen(
                    modifier = Modifier,
                    onGetStarted = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Navigation.NewsNavigation.route,
            startDestination = Screen.NewsNavigatorScreen.route
        ) {
            composable(
                route = Screen.NewsNavigatorScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    articles = viewModel.articles.collectAsLazyPagingItems(),
                    onNavigateToSearchScreen = { route ->

                    },
                    onNavigateToDetailsScreen = { route ->

                    }
                )
//                val viewModel: SearchViewModel = hiltViewModel()
//                SearchScreen(
//                    searchState = viewModel.searchState,
//                    onSearchQueryUpdated = viewModel::onEvent,
//                    onSearchArticles = viewModel::onEvent,
//                    onNavigateToDetailsScreen = { route ->
//
//                    }
//                )
//                DetailsScreen(
//                    article = Article(
//                        author = "kcloonan@insider.com (Kelly Cloonan)",
//                        content = "As crypto rallies, the number of bitcoin millionaires has more than doubled in the last year. According to a new report from New World Wealth and Henley &amp; Partners, the number of bitcoin million… [+2414 chars]",
//                        description = "The number of bitcoin millionaires has soared as  the crypto rallies. There have also been six new crypto billionaires minted in the past year.",
//                        publishedAt = "2024-08-27T18:50:37Z",
//                        source = Source(id = "business-insider", name = "Business Insider"),
//                        title = "The number of bitcoin millionaires has soared 111% in the last year as the cryptocurrency rallies",
//                        url = "https://markets.businessinsider.com/news/currencies/bitcoin-millionaires-crypto-rally-cryptocurrency-etf-billionaires-sec-ethereum-wealth-2024-8",
//                        urlToImage = "https://www.businessinsider.com/public/assets/logos/og-image-logo-social.png?v=2023-11",
//                    ),
//                    onNavigateBack = {},
//                    onBookmarkClick = { _ ->  }
//                )
            }
        }
    }
}