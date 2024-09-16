package com.example.newsapp.presentation.details.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import com.example.newsapp.presentation.details.event.DetailsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val articlesUseCases: ArticlesUseCases
): ViewModel() {
    var message by mutableStateOf<String?>(null)
        private set

    private var bookmarkArticle by mutableStateOf<Article?>(null)


    fun onEvent(bookmarkEvent: DetailsEvent) {
        when (bookmarkEvent) {
            is DetailsEvent.GetBookmarkArticle -> {
                viewModelScope.launch {
                    getBookmarkArticle(bookmarkEvent.url)
                }
            }
            is DetailsEvent.BookmarkArticle -> {
                viewModelScope.launch {
                    if (!isAlreadyBookmarked()) {
                        insertArticle(bookmarkEvent.article)
                    } else {
                        deleteArticle(bookmarkEvent.article)
                    }
                }
            }
            is DetailsEvent.RemoveMessage -> {
                message = null
            }
        }
    }

    private suspend fun getBookmarkArticle(url: String) {
        bookmarkArticle = articlesUseCases.getSingleBookmarkArticle.invoke(url)
    }

    fun isAlreadyBookmarked(): Boolean {
        return bookmarkArticle != null
    }

    private suspend fun insertArticle(article: Article) {
        articlesUseCases.insertBookmarkArticle.invoke(article)
        message = "Successfully saved the article!"
    }

    private suspend fun deleteArticle(article: Article) {
        articlesUseCases.deleteBookmarkArticle.invoke(article)
        message = "Successfully removed the article!"
    }
}