package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.manager.LocalUserManagerImplementation
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecases.articles.ArticlesUseCases
import com.example.newsapp.domain.usecases.articles.GetArticles
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
        return LocalUserManagerImplementation(
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
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsApi = newsApi
        )
    }

    @Provides
    @Singleton
    fun provideArticlesUseCases(
        newsRepository: NewsRepository
    ): ArticlesUseCases {
        return ArticlesUseCases(
            getArticles = GetArticles(
                newsRepository = newsRepository
            )
        )
    }
}