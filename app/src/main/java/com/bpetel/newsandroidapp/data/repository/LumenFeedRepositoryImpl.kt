package com.bpetel.newsandroidapp.data.repository

import com.bpetel.newsandroidapp.data.remote.LumenFeedApi
import com.bpetel.newsandroidapp.data.remote.model.toDomain
import com.bpetel.newsandroidapp.domain.Article
import com.bpetel.newsandroidapp.domain.LumenFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LumenFeedRepositoryImpl(
    private val api: LumenFeedApi
) : LumenFeedRepository {
    override suspend fun getArticles(): Flow<List<Article>> {
        val articleList = mutableListOf<Article>()

        api.getArticles().body()?.data?.forEach { articleDto ->
            articleList.add(articleDto.toDomain())
        }

        return flow {
            emit(articleList)
        }
    }
}