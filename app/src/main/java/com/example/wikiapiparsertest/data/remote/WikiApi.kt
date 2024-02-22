package com.example.wikiapiparsertest.data.remote

import com.example.wikiapiparsertest.data.remote.response.WikiResponseDto
import com.example.wikiapiparsertest.data.remote.response.page.PageDto
import com.example.wikiapiparsertest.data.remote.response.search.WikiSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("w/api.php/")
    suspend fun get(
        @Query("action") action: String = "parse",
        @Query("page") page: String = "Pet_door",
        @Query("format") format: String = "json"
    ): WikiResponseDto

    @GET("w/api.php/")
    suspend fun search(
        @Query("action") action: String = "query",
        @Query("list") list: String = "search",
        @Query("srsearch") query: String = "pet door",
        @Query("format") format: String = "json"
    ): WikiSearchDto

    @GET("w/api.php/")
    suspend fun getPageById(
        @Query("action") action: String = "query",
        @Query("prop") prop: String = "extracts",
        @Query("pageids") id: Int = 0,
        @Query("format") format: String = "json"
    ): PageDto

}