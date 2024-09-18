package com.example.novelsbookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.novelsbookshelf.NovelsApplication
import com.example.novelsbookshelf.model.Item
import com.example.novelsbookshelf.model.Novel
import com.example.novelsbookshelf.repository.NovelsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface SearchState {
    data class Success(val novels: MutableList<Novel>): SearchState
    object Loading: SearchState
    object  Error: SearchState
}

data class NovelUiState(
    var searchState: SearchState,
    var query: String
)

class NovelsViewModel(private val novelsRepository: NovelsRepository): ViewModel(){
    private val _uiState: MutableStateFlow<NovelUiState> = MutableStateFlow(NovelUiState(SearchState.Loading, ""))
    val uiState = _uiState.asStateFlow()

    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }
    fun getNovels(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(searchState = SearchState.Loading) }
            _uiState.update {
                it.copy(
                    searchState = try {
                        val firstCallResult = novelsRepository.getItems(query)
                        val novels = makeNovelList(firstCallResult)
                        SearchState.Success(novels)
                    } catch (e: IOException) {
                        SearchState.Error
                    } catch (e: HttpException) {
                        SearchState.Error
                    }
                )
            }
        }
    }

    private suspend fun makeNovelList(firstCallResult: List<Item>): MutableList<Novel> {
        val novelsList: MutableList<Novel> = mutableListOf()
        firstCallResult.forEach {
            val thumbnail = novelsRepository.getThumbnailUrl(it)
            val title = novelsRepository.getTitle(it)
            val novel =  Novel(
                id = it.id,
                title = title,
                thumbnailUrl = thumbnail
            )
            novelsList.add(novel)
        }
        return novelsList
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NovelsApplication)
                val novelsRepository = application.contaier.novelsRepository
                NovelsViewModel(novelsRepository)
            }
        }
    }
}