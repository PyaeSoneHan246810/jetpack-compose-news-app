package com.example.newsapp.domain.usecases.articles

data class ArticlesUseCases(
    val getArticles: GetArticles,
    val searchArticles: SearchArticles,
    val insertBookmarkArticle: InsertBookmarkArticle,
    val deleteBookmarkArticle: DeleteBookmarkArticle,
    val getBookmarkArticles: GetBookmarkArticles,
    val getSingleBookmarkArticle: GetSingleBookmarkArticle,
)
