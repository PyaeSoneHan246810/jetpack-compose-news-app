package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newsapp.presentation.newsNavigator.screen.NewsNavigatorScreen
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
                    onGetStarted = viewModel::onEvent,
                    onNavigateToNewsNavigatorScreen = { route ->
                        navController.navigate(route) {
                            popUpTo(Screen.OnBoardingScreen.route) {
                                inclusive = true
                            }
                        }
                    }
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
                NewsNavigatorScreen(
                    modifier = Modifier
                )
            }
        }
    }
}