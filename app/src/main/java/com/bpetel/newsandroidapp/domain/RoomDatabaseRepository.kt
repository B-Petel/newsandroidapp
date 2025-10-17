package com.bpetel.newsandroidapp.domain

import com.bpetel.newsandroidapp.data.local.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface RoomDatabaseRepository {
    suspend fun getArticles(): Flow<List<ArticleEntity>>
}