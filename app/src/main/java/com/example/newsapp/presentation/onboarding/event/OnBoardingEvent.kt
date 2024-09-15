package com.example.newsapp.presentation.onboarding.event

sealed class OnBoardingEvent {
    data object SaveAppEntry: OnBoardingEvent()
    data object ReadAppEntry: OnBoardingEvent()
}