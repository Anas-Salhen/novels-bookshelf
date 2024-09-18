package com.example.novelsbookshelf.repository

import com.example.novelsbookshelf.model.Item
import com.example.novelsbookshelf.network.NovelsApiService

interface NovelsRepository {
    suspend fun getItems(query: String): List<Item>

    suspend fun getThumbnailUrl(item: Item): String

    fun getTitle(item: Item): String
}

class NovelsRepositoryImpl(
    private val novelsApiService: NovelsApiService
): NovelsRepository {
    override suspend fun getItems(query: String): List<Item> = novelsApiService.getItems(query).items

    override suspend fun getThumbnailUrl(item: Item): String {
        return item.volumeInfo.imageLinks.thumbnailUrl
    }

    override fun getTitle(item: Item): String {
        return item.volumeInfo.title
    }
}