package com.example.wikiapiparsertest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Text(
    @SerializedName("*") val text: String
)