package com.example.wikiapiparsertest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Template(
    @SerializedName("*") val name: String,
    val exists: String,
    val ns: Int
)