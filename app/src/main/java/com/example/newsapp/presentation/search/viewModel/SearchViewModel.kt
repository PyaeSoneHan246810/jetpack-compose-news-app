package com.example.newsapp.presentation.search.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import com.example.newsapp.presentation.search.event.SearchEvent
import com.example.newsapp.presentation.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val articlesUseCases: ArticlesUseCases
): ViewModel() {
    var searchState by mutableStateOf(SearchState())
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                searchState = searchState.copy(
                    searchQuery = event.searchQuery
                )
            }
            is SearchEvent.SearchArticles -> {
                searchState = searchState.copy(
                    searchArticles = searchArticles(searchQuery = searchState.searchQuery)
                )
            }
            is SearchEvent.RefreshArticles -> {
                isRefreshing = true
                searchState = searchState.copy(
                    searchArticles = searchArticles(searchQuery = searchState.searchQuery)
                )
                isRefreshing = false
            }
        }
    }

    private fun searchArticles(searchQuery: String): Flow<PagingData<Article>> {
        return articlesUseCases.searchArticles.invoke(
            searchQuery = searchQuery,
            sources = listOf("bbc-news", "abc-news")
        ).cachedIn(viewModelScope)
    }
}