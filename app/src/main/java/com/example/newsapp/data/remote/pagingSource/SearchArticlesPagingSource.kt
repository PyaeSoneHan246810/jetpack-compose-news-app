package com.example.newsapp.data.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.model.Article

class SearchArticlesPagingSource(
    private val newsApi: NewsApi,
    private val sources: String,
    private val searchQuery: String
): PagingSource<Int, Article>() {
    private var totalArticlesCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val searchArticlesResponse = newsApi.searchArticles(
                searchQuery = searchQuery,
                apiKey = BuildConfig.API_KEY,
                sources = sources,
                page = page
            )
            totalArticlesCount += searchArticlesResponse.articles.size
            LoadResult.Page(
                data = searchArticlesResponse.articles.distinctBy {
                    it.title
                },
                nextKey = if (totalArticlesCount == searchArticlesResponse.totalResults) null else page + 1,
                prevKey = null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}