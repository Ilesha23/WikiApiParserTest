package com.example.wikiapiparsertest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Iwlink(
    @SerializedName("*") val wtf: String,
    val prefix: String,
    val url: String
)