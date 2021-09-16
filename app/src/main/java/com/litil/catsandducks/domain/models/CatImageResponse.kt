package com.litil.catsandducks.domain.models

data class CatImageResponse(
    val id: Int,
    val url: String,
    val webpurl: String,
    val x: Double,
    val y: Double
)
