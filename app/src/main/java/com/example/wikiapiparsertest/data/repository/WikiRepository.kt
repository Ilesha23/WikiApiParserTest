package com.example.wikiapiparsertest.data.repository

import kotlinx.coroutines.flow.Flow

interface WikiRepository {

    suspend fun getFromWiki(pageName: String?): Flow<Result<String>>

    suspend fun parse(html: String): String

}