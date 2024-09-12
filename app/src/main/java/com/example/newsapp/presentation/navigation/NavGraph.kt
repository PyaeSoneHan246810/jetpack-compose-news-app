package com.example.newsapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Welcome"
                        )
                    }
                }
            }
        }
    }
}