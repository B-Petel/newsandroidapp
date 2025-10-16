package com.bpetel.newsandroidapp.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articleentity")
    fun getAll(): List<ArticleEntity>
}