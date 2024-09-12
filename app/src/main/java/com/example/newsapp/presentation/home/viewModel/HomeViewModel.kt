package com.example.newsapp.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var articlesUseCases: ArticlesUseCases
): ViewModel() {
    val articles = articlesUseCases.getArticles.invoke(sources = listOf("bbc-news", "abc-news"))
        .cachedIn(viewModelScope)
}