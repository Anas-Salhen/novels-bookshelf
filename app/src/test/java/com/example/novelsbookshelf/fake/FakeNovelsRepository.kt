package com.example.novelsbookshelf.fake

import com.example.novelsbookshelf.model.Item
import com.example.novelsbookshelf.network.NovelsApiService
import com.example.novelsbookshelf.repository.NovelsRepository

class FakeNovelsRepository(
    private val novelsApiService: NovelsApiService
): NovelsRepository {
    override suspend fun getItems(): MutableList<Item> = novelsApiService.getItems().items

    override suspend fun getThumbnailUrl(item: Item): String {
        return item.volumeInfo.imageLinks.thumbnailUrl
    }

    override fun getTitle(item: Item): String? {
        return item.volumeInfo.title
    }
}