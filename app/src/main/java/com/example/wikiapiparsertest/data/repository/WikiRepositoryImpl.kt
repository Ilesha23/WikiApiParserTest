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

    override suspend fun getFromWiki(pageName: String?): Flow<Result<String>> {
        return flow {
            try {
                val response = if (!pageName.isNullOrBlank()) {
                    val name = pageName.replace(" ", "_")
                    withContext(Dispatchers.IO) {
                        api.get(page = name)
                    }
                } else {
                    withContext(Dispatchers.IO) {
                        api.get()
                    }
                }
                if (response.parse.text.text.isNotBlank()) {
                    emit(Result.success(response.parse.text.text))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun parse(html: String): Map<String, String> = withContext(Dispatchers.IO) {
        try {
            val document = Jsoup.parse(html)
            val paragraphs = mutableMapOf<String, String>()

            var currentHeader = ""
            document.body().children().forEach { element ->
                if (element.tagName() == "h2") {
                    currentHeader = element.text()
                } else if (element.tagName() == "p") {
                    paragraphs[currentHeader] = paragraphs.getOrDefault(currentHeader, "") + element.text() + "\n"
                }
            }

            if (currentHeader.isNotEmpty()) {
                paragraphs[currentHeader] = paragraphs.getOrDefault(currentHeader, "")
            }

            return@withContext paragraphs
        } catch (e: Exception) {
            return@withContext mapOf("" to e.message.orEmpty())
        }
    }



    override suspend fun search(pageName: String?): Flow<Result<String>> {
        return flow {
            try {
                val response = withContext(Dispatchers.IO) { api.search(query = pageName ?: "") }
                val pageId = response.query.search.firstOrNull()?.pageid
                    ?: throw NoSuchElementException("No page ID found")
                val page = withContext(Dispatchers.IO) { api.getPageById(id = pageId) }
                val x9232 = page.query.pages[pageId.toString()]
                val result = x9232?.extract ?: ""
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

//    override suspend fun getPageById(id: Int): Flow<Result<String>> {
//        return flow {
//            try {
//                withContext(Dispatchers.IO) {
//                    val response = api.getPageById(id = id)
//                    emit(Result.success(response.query.pages.wtf.extract))
//                }
//            } catch (e: Exception) {
//                emit(Result.failure(e))
//            }
//        }
//    }


}