package com.example.novelsbookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.novelsbookshelf.R
import com.example.novelsbookshelf.model.Novel
import com.example.novelsbookshelf.ui.SearchState
import com.example.novelsbookshelf.ui.theme.NovelsBookshelfTheme

@Composable
fun ResultsScreen(
    searchState: SearchState,
    retryAction: () -> Unit,
    contentPadding: PaddingValues
    ) {
    Column(modifier = Modifier.padding(contentPadding)) {
        when (searchState) {
            is SearchState.Success -> NovelsGrid(novels = searchState.novels)
            is SearchState.Loading -> LoadingScreen()
            is SearchState.Error -> ErrorScreen(retryAction)
        }
    }
}

@Composable
fun NovelCard(novel: Novel) {
    val TAG = "Novel Card"
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current).data(novel.thumbnailUrl)
            .crossfade(true).build(),
        contentDescription = novel.title,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        modifier = Modifier
            .padding(2.dp)
            .height(200.dp),
        onError = {
            Log.d(TAG, "Error: ${it.result.throwable}")
        }
    )
}

@Composable
fun NovelsGrid(novels: List<Novel>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(130.dp)) {
        items(items = novels, key = { novel -> novel.id }) {
            NovelCard(it)
        }
    }
}

@Composable
fun LoadingScreen() {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ErrorScreen(retryAction: ()-> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Text(text = stringResource(R.string.connection_error), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NovelsPreview() {
    NovelsBookshelfTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            ResultsScreen(
                searchState = SearchState.Loading,
                contentPadding = PaddingValues(0.dp),
                retryAction = {}
            )
        }
    }
}