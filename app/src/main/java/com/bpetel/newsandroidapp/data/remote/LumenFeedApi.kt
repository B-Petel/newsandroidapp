package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.remote.model.ArticleListDto
import retrofit2.Response
import retrofit2.http.GET

interface LumenFeedApi {
    @GET("articles")
    suspend fun getArticles(): Response<ArticleListDto>
}
