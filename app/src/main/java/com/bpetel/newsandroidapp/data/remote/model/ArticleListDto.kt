package com.bpetel.newsandroidapp.data.remote.model

data class ArticleListDto(
    val data: List<ArticleDto> = emptyList(),
    val meta: MetaDataDto = MetaDataDto()
)