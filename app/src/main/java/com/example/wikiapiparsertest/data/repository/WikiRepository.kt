package com.example.wikiapiparsertest.data.repository

import com.example.wikiapiparsertest.data.remote.response.search.Search
import kotlinx.coroutines.flow.Flow

interface WikiRepository {

    suspend fun getFromWiki(pageName: String?): Flow<Result<String>>

    suspend fun parse(html: String): Map<String, String>

    suspend fun search(pageName: String?): Flow<Result<String>>

//    suspend fun getPageById(id: Int): Flow<Result<String>>

}