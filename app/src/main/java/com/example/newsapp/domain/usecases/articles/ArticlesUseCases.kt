package com.example.newsapp.domain.usecases.articles

data class ArticlesUseCases(
    var getArticles: GetArticles,
    var searchArticles: SearchArticles,
    var insertBookmarkArticle: InsertBookmarkArticle,
    var deleteBookmarkArticle: DeleteBookmarkArticle,
    var getBookmarkArticles: GetBookmarkArticles
)
