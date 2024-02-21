package com.example.wikiapiparsertest.data.remote

import com.example.wikiapiparsertest.data.remote.response.WikiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("w/api.php/")
    suspend fun get(
        @Query("action") action: String = "parse",
        @Query("page") page: String = "Pet_door",
        @Query("format") format: String = "json"
    ): WikiResponseDto

}