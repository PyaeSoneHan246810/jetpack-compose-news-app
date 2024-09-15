package com.example.newsapp.presentation.onboarding.uiData

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Stay Updated",
        description = "Welcome to our app! Get instant access to breaking news from trusted sources around the globe.",
        image = R.drawable.img_onboarding1,
    ),
    Page(
        title = "Explore Trending News",
        description = "Discover the latest headlines and trending news from around the world, from politics to pop culture.",
        image = R.drawable.img_onboarding2,
    ),
    Page(
        title = "Gateway to Information",
        description = "You have instant access to updated news wherever you are. Tap \"Get Started\" to start using out app. ",
        image = R.drawable.img_onboarding3,
    )
)
