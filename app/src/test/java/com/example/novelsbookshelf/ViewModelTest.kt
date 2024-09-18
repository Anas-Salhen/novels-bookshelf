package com.example.novelsbookshelf

import com.example.novelsbookshelf.fake.FakeDataSource
import com.example.novelsbookshelf.fake.FakeNovelsApiService
import com.example.novelsbookshelf.fake.FakeNovelsRepository
import com.example.novelsbookshelf.rule.TestDispatcherRule
import com.example.novelsbookshelf.ui.SearchState
import com.example.novelsbookshelf.ui.NovelsViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ViewModelTest {

    private val repository = FakeNovelsRepository(FakeNovelsApiService())

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun novelsViewModel_getNovels_verifyNovels() {
        val fakeNovelsViewModel = NovelsViewModel(repository)
        if (fakeNovelsViewModel.uiState.value.searchState is SearchState.Success) {
            assertEquals(
                FakeDataSource.novels,
                (fakeNovelsViewModel.uiState.value.searchState as SearchState.Success).novels
            )
        } else {
            throw AssertionError()
        }
    }
}