package com.example.wikiapiparsertest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Property(
    @SerializedName("*") val someShitThatIdk: String,
    val name: String
)