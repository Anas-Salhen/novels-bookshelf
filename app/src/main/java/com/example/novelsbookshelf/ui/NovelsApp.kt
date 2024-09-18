package com.example.novelsbookshelf.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.novelsbookshelf.R
import com.example.novelsbookshelf.ui.screens.ResultsScreen
import com.example.novelsbookshelf.ui.theme.NovelsBookshelfTheme

@Composable
fun NovelsApp() {
    val novelsViewModel: NovelsViewModel = viewModel(factory = NovelsViewModel.Factory)
    val novelUiState = novelsViewModel.uiState.collectAsState()
    Scaffold(
        topBar = { NovelsTopAppBar() }
    ) {
        Search(
            query = novelUiState.value.query,
            onQueryChange = { novelsViewModel.onQueryChange(it) },
            onSearch = { novelsViewModel.getNovels(it) },
            isActive = false
        )
        ResultsScreen(
            searchState = novelUiState.value.searchState,
            retryAction = { novelsViewModel.getNovels(novelUiState.value.query) },
            contentPadding = it
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovelsTopAppBar() {
    CenterAlignedTopAppBar(title = {
        Text(stringResource(R.string.app_name))
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    isActive: Boolean
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isActive,
        onActiveChange = {/*TODO*/},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        leadingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        },
    ) {}
}

@Preview
@Composable
fun NovelsPreview() {
    NovelsBookshelfTheme {
        NovelsApp()
    }
}