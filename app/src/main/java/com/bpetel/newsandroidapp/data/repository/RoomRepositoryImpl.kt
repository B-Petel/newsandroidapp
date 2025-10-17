package com.bpetel.newsandroidapp.data.repository

import com.bpetel.newsandroidapp.data.local.AppDatabase
import com.bpetel.newsandroidapp.data.local.ArticleEntity
import com.bpetel.newsandroidapp.domain.RoomDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomRepositoryImpl(
    private val appDatabase: AppDatabase
): RoomDatabaseRepository {
    override suspend fun getArticles(): Flow<List<ArticleEntity>> {
        return flow { appDatabase.articleDao().getAll() }
    }
}