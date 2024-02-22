package com.example.wikiapiparsertest.data.remote.response.page

data class PageDto(
    val batchcomplete: String,
    val query: Query,
    val warnings: Warnings
)