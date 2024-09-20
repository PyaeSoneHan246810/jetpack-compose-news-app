package com.example.newsapp.presentation.home.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import com.example.newsapp.presentation.home.event.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var articlesUseCases: ArticlesUseCases
): ViewModel() {
    var articles by mutableStateOf<Flow<PagingData<Article>>?>(null)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    init {
        getArticles()
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.RefreshArticles -> {
                isRefreshing = true
                getArticles()
                isRefreshing = false
            }
        }
    }

    private fun getArticles() {
        articles = articlesUseCases.getArticles.invoke(sources = listOf("bbc-news", "abc-news"))
            .cachedIn(viewModelScope)
    }
}