package com.example.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.local.dao.BookmarkArticlesDao
import com.example.newsapp.data.local.typeConverter.BookmarkArticleTypeConverter
import com.example.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(BookmarkArticleTypeConverter::class)
abstract class BookmarkArticlesDatabase: RoomDatabase() {
    abstract val bookmarkArticlesDao: BookmarkArticlesDao
}
