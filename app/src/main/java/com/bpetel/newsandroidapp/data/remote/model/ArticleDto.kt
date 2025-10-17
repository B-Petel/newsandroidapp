package com.bpetel.newsandroidapp.data.remote.model

import com.bpetel.newsandroidapp.domain.Article
import com.google.gson.annotations.SerializedName
import kotlin.String

data class ArticleDto(
    val author: String,
    @SerializedName("content_excerpt") val contentExcerpt: String,
    val country: String,
    @SerializedName("full_content_html") val fullContentHtml: String,
    @SerializedName("full_content_sanitized") val fullContentSanitized: String?,
    @SerializedName("has_video") val hasVideo: Boolean,
    val id: String,
    @SerializedName("image_url") val imageUrl: String,
    val keywords: List<String>,
    val language: String,
    @SerializedName("published_at") val publishedAt: Int,
    @SerializedName("publisher_id") val publisherId: String,
    @SerializedName("sentiment_label") val sentimentLabel: String,
    @SerializedName("sentiment_score") val sentimentScore: Double,
    @SerializedName("source_link") val sourceLink: String,
    val title: String,
    @SerializedName("topic_id") val topicId: String
)

fun ArticleDto.toDomain(): Article {
    return Article(
        id = id,
        publisherId = publisherId,
        author = author,
        title = title,
        contentExcerpt = contentExcerpt,
        fullContentSanitized = fullContentSanitized,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        sentimentLabel = sentimentLabel,
        sentimentScore = sentimentScore
    )
}