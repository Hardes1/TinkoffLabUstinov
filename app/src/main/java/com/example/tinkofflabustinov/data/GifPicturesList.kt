package com.example.tinkofflabustinov.data

import com.squareup.moshi.Json

data class GifPicturesList(
    @Json(name = "result") val gifPictures: List<GifPicture>?,
    val totalCount: Int?
)