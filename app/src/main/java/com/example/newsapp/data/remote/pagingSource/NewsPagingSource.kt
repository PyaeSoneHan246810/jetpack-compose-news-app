package com.example.newsapp.data.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String,
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
            val newsResponse = newsApi.getArticles(
                apiKey = BuildConfig.API_KEY,
                sources = sources,
                page = page
            )
            totalArticlesCount += newsResponse.articles.size
            LoadResult.Page(
                data = newsResponse.articles.distinctBy {
                    it.title
                },
                nextKey = if (totalArticlesCount == newsResponse.totalResults) null else page + 1,
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