package com.example.newsapp.domain.usecases.articles

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getArticles(
            sources = sources
        )
    }
}