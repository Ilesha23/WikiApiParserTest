package com.example.wikiapiparsertest.data.repository

import com.example.wikiapiparsertest.data.remote.WikiApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class WikiRepositoryImpl @Inject constructor(
    private val api: WikiApi
) : WikiRepository {

    override suspend fun getFromWiki(): Flow<Result<String>> {
        return flow {
            try {
                val response = api.get()
                emit(Result.success(response.parse.text.text))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun parse(html: String): String =
        withContext(Dispatchers.IO) {
            try {
                val text = Jsoup.parse(html)
                return@withContext text.text()
            } catch (e: Exception) {
                return@withContext e.message.orEmpty()
            }
        }


}