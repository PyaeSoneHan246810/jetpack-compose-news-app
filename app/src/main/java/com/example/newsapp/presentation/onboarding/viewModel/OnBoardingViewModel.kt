package com.example.newsapp.presentation.onboarding.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.onboarding.events.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
            is OnBoardingEvent.ReadAppEntry -> {}
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry.invoke()
        }
    }
}