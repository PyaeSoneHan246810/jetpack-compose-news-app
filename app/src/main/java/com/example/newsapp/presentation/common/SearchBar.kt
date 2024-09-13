package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.CORNER_SM
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    readOnly: Boolean,
    value: String,
    onValueChange: (newValue: String) -> Unit,
    onSearch: () -> Unit,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isSearchBarClicked by interactionSource.collectIsPressedAsState()
    LaunchedEffect(key1 = isSearchBarClicked) {
        if (isSearchBarClicked) {
            onClick?.invoke()
        }
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(CORNER_SM))
            .searchBarBorder(),
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyLarge
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = if (!isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.outline.copy(
                alpha = 0.4f
            ),
            focusedContainerColor = if (!isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.outline.copy(
                alpha = 0.4f
            ),
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        interactionSource = interactionSource
    )
}

fun Modifier.searchBarBorder() = composed {
    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            shape = RoundedCornerShape(CORNER_SM)
        )
    } else {
        this
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun SearchBarPrev() {
    NewsAppTheme {
        Surface {
            SearchBar(
                readOnly = false,
                value = "Test",
                onValueChange = {newValue ->  },
                onSearch = { /*TODO*/ }
            )
        }
    }
}