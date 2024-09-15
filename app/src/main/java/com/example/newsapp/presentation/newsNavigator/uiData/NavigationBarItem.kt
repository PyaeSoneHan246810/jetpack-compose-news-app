package com.example.newsapp.presentation.newsNavigator.uiData

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsapp.R

data class NavigationBarItem(
    val icon: ImageVector,
    @StringRes val label: Int
)

val navigationBarItems = listOf(
    NavigationBarItem(
        icon = Icons.Outlined.Home,
        label = R.string.home
    ),
    NavigationBarItem(
        icon = Icons.Outlined.Search,
        label = R.string.search
    ),
    NavigationBarItem(
        icon = Icons.Outlined.BookmarkBorder,
        label = R.string.bookmarks
    )
)