package com.example.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.local.dao.ArticlesDao
import com.example.newsapp.data.local.typeConverter.ArticleTypeConverter
import com.example.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(ArticleTypeConverter::class)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract val articlesDao: ArticlesDao
}
