package com.bpetel.newsandroidapp.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<ArticleEntity>

    @Insert
    fun insertAll(vararg articles: ArticleEntity)

    @Delete
    fun delete(article: ArticleEntity)
}