package com.example.novelsbookshelf

import com.example.novelsbookshelf.fake.FakeDataSource
import com.example.novelsbookshelf.fake.FakeNovelsApiService
import com.example.novelsbookshelf.fake.FakeNovelsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NovelsRepositoryTest {
    private val repository = FakeNovelsRepository(FakeNovelsApiService())

    @Test
    fun novelsRepository_getItems_verifyItems() = runTest {
        assertEquals(FakeDataSource.nestedResponse.items, repository.getItems())
    }

    @Test
    fun novelsRepository_getThumbnailUrl_verifyThumbnail() = runTest {
        val item = FakeDataSource.nestedResponse.items[0]
        assertEquals(
            FakeDataSource.nestedResponse.items[0].volumeInfo.imageLinks.thumbnailUrl,
            repository.getThumbnailUrl(item)
        )
    }

    @Test
    fun novelsRepository_getTitle_verifyTitle() = runTest {
        val item = FakeDataSource.nestedResponse.items[0]
        assertEquals(
            FakeDataSource.nestedResponse.items[0].volumeInfo.title,
            repository.getTitle(item)
        )
    }
}