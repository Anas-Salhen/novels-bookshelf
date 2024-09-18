package com.example.novelsbookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NestedResponse(
    val items: List<Item>
)

@Serializable
data class Item(
    val id: String,
    val volumeInfo: Info
)

@Serializable
data class Info(
    val title: String,
    val imageLinks: ImageLinks = ImageLinks("null")
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    val thumbnailUrl: String
)
data class Novel(
    val id: String,
    val title: String,
    val thumbnailUrl: String
)