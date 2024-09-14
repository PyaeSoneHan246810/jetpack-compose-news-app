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
import com.example.newsapp.presentation.search.events.SearchEvent
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

    fun onEvent(event: SearchEvent) {
        searchState = when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                searchState.copy(
                    searchQuery = event.searchQuery
                )
            }
            is SearchEvent.SearchArticles -> {
                searchState.copy(
                    searchArticles = searchArticles(searchQuery = searchState.searchQuery)
                )
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