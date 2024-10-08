package com.example.newsapp.presentation.bookmark.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import com.example.newsapp.presentation.bookmark.BookmarkEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor (
    private val articlesUseCases: ArticlesUseCases
): ViewModel() {
    var articles by mutableStateOf<List<Article>>(emptyList())
        private set

    init {
        getArticles()
    }

    fun onEvent(event: BookmarkEvent) {
        when(event) {
            is BookmarkEvent.RemoveBookmarkArticle -> {
                viewModelScope.launch {
                    articlesUseCases.deleteBookmarkArticle.invoke(event.article)
                }
            }
        }
    }

    private fun getArticles() {
        articlesUseCases.getBookmarkArticles.invoke().onEach {  bookmarkArticles ->
            articles = bookmarkArticles
        }.launchIn(viewModelScope)
    }
}