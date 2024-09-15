package com.example.newsapp.domain.usecases.articles

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow

class SearchArticles(
    private val articlesRepository: ArticlesRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return articlesRepository.searchArticles(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}