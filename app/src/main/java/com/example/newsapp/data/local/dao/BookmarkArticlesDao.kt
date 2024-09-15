package com.example.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkArticle(article: Article)

    @Delete
    suspend fun deleteBookmarkArticle(article: Article)

    @Query("SELECT * FROM article")
    fun getBookmarkArticles(): Flow<List<Article>>
}