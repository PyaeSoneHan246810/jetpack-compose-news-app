package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.navigation.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashScreenVisible
            }
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.Transparent.toArgb(), Color.Transparent.toArgb()),
            navigationBarStyle = SystemBarStyle.auto(Color.Transparent.toArgb(), Color.Transparent.toArgb())
        )
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme{
                navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    startDestination = mainViewModel.startNavigation
                )
            }
        }
    }
}