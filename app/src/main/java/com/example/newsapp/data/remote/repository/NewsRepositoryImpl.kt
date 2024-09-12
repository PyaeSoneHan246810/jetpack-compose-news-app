package com.example.newsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.pagingSource.NewsPagingSource
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
): NewsRepository {
    override fun getArticles(sources: List<String>): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        )
        return pager.flow
    }
}