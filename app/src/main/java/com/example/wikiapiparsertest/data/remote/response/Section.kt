package com.example.wikiapiparsertest.data.remote.response

data class Section(
    val anchor: String,
    val byteoffset: Int,
    val fromtitle: String,
    val index: String,
    val level: String,
    val line: String,
    val linkAnchor: String,
    val number: String,
    val toclevel: Int
)