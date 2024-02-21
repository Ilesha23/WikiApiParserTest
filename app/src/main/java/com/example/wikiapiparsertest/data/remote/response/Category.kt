package com.example.wikiapiparsertest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("*") val what: String,
    val hidden: String,
    val sortkey: String
)