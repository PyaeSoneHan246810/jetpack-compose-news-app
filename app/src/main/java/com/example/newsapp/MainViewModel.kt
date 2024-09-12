package com.example.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {
    var splashScreenVisible by mutableStateOf(true)
        private set
    var startNavigation by mutableStateOf(Navigation.AppStartNavigation.route)
        private set
    init {
        appEntryUseCases.readAppEntry.invoke().onEach {  alreadyEntered ->
            startNavigation = if (!alreadyEntered) {
                Navigation.AppStartNavigation.route
            } else {
                Navigation.NewsNavigation.route
            }
            delay(300)
            splashScreenVisible = false
        }.launchIn(viewModelScope)
    }
}