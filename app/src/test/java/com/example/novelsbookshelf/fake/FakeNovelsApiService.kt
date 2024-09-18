package com.example.novelsbookshelf.fake

import com.example.novelsbookshelf.model.ImageLinks
import com.example.novelsbookshelf.model.Item
import com.example.novelsbookshelf.model.NestedResponse
import com.example.novelsbookshelf.network.NovelsApiService

class FakeNovelsApiService: NovelsApiService {
    override suspend fun getItems(): NestedResponse {
        return FakeDataSource.nestedResponse
    }

    /*override suspend fun getThumbnailUrl(id: String): ImageLinks {
        lateinit var targetItem: Item
        FakeDataSource.nestedResponse.items.forEach {
            if (it.id == id) {
                targetItem = it
            }
        }
        return targetItem.volumeInfo.imageLinks
    }*/
}