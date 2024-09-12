package com.example.newsapp.presentation.onboarding.events

sealed class OnBoardingEvent {
    data object SaveAppEntry: OnBoardingEvent()
    data object ReadAppEntry: OnBoardingEvent()
}