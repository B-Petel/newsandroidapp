package com.bpetel.newsandroidapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey val uid: Int,
)
