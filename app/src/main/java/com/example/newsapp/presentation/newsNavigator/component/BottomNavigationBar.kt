package com.example.newsapp.presentation.newsNavigator.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.newsNavigator.uiData.NavigationBarItem
import com.example.newsapp.presentation.newsNavigator.uiData.navigationBarItems
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun BottomNavigationBar (
    modifier: Modifier = Modifier,
    navigationBarItems: List<NavigationBarItem>,
    selectedItemIndex: Int,
    onItemClick: (clickedItemIndex: Int) -> Unit
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 2.dp,
    ) {
        navigationBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItemIndex,
                onClick = {
                    onItemClick(index)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.label),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.label)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.secondary,
                    selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun BottomNavigationBarPrev() {
    NewsAppTheme {
        BottomNavigationBar(
            navigationBarItems = navigationBarItems,
            selectedItemIndex = 0,
            onItemClick = { _ ->  }
        )
    }
}