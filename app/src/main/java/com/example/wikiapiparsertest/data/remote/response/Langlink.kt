package com.example.wikiapiparsertest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Langlink(
    @SerializedName("*") val original: String,
    val autonym: String,
    val lang: String,
    val langname: String,
    val url: String
)