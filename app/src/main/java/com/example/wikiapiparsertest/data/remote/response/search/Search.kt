package com.example.wikiapiparsertest.data.remote.response.search

data class Search(
    val ns: Int,
    val pageid: Int,
    val size: Int,
    val snippet: String,
    val timestamp: String,
    val title: String,
    val wordcount: Int
)