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
    fun onEvent(bookmarkEvent: DetailsEvent) {
        when (bookmarkEvent) {
            is DetailsEvent.BookmarkArticle -> {
                viewModelScope.launch {
                    val article = bookmarkEvent.article
                    if (isAlreadyBookmarked(article.url)) {
                        insertArticle(article)
                    } else {
                        deleteArticle(article)
                    }
                }
            }
        }
    }

    private suspend fun isAlreadyBookmarked(url: String): Boolean {
        val article = articlesUseCases.getSingleBookmarkArticle.invoke(url)
        return article == null
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