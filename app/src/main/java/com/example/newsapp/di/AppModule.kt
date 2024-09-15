package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.dao.BookmarkArticlesDao
import com.example.newsapp.data.local.database.BookmarkArticlesDatabase
import com.example.newsapp.data.local.repository.BookmarkArticlesRepositoryImpl
import com.example.newsapp.data.local.typeConverter.BookmarkArticleTypeConverter
import com.example.newsapp.data.manager.LocalUserManagerImpl
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.repository.ArticlesRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.ArticlesRepository
import com.example.newsapp.domain.repository.BookmarkArticlesRepository
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import com.example.newsapp.domain.usecases.articles.DeleteBookmarkArticle
import com.example.newsapp.domain.usecases.articles.GetArticles
import com.example.newsapp.domain.usecases.articles.GetBookmarkArticles
import com.example.newsapp.domain.usecases.articles.InsertBookmarkArticle
import com.example.newsapp.domain.usecases.articles.SearchArticles
import com.example.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        @ApplicationContext context: Context
    ): LocalUserManager {
        return LocalUserManagerImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases {
        return AppEntryUseCases(
            saveAppEntry = SaveAppEntry(
                localUserManager = localUserManager
            ),
            readAppEntry = ReadAppEntry(
                localUserManager = localUserManager
            )
        )
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookmarkArticlesDatabase(
        @ApplicationContext context: Context
    ): BookmarkArticlesDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = BookmarkArticlesDatabase::class.java,
                name = Constants.ARTICLES_DATABASE_NAME
            )
            .addTypeConverter(BookmarkArticleTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookmarkArticlesDao(
        bookmarkArticlesDatabase: BookmarkArticlesDatabase
    ): BookmarkArticlesDao {
        return bookmarkArticlesDatabase.bookmarkArticlesDao
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(
        newsApi: NewsApi
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            newsApi = newsApi
        )
    }

    @Provides
    @Singleton
    fun provideBookmarkArticlesRepository(
        bookmarkArticlesDao: BookmarkArticlesDao
    ): BookmarkArticlesRepository {
        return BookmarkArticlesRepositoryImpl(
            bookmarkArticlesDao = bookmarkArticlesDao
        )
    }

    @Provides
    @Singleton
    fun provideArticlesUseCases(
        articlesRepository: ArticlesRepository,
        bookmarkArticlesRepository: BookmarkArticlesRepository
    ): ArticlesUseCases {
        return ArticlesUseCases(
            getArticles = GetArticles(
                articlesRepository = articlesRepository
            ),
            searchArticles = SearchArticles(
                articlesRepository = articlesRepository
            ),
            insertBookmarkArticle = InsertBookmarkArticle(
                bookmarkArticlesRepository = bookmarkArticlesRepository
            ),
            deleteBookmarkArticle = DeleteBookmarkArticle(
                bookmarkArticlesRepository = bookmarkArticlesRepository
            ),
            getBookmarkArticles = GetBookmarkArticles(
                bookmarkArticlesRepository = bookmarkArticlesRepository
            )
        )
    }
}
