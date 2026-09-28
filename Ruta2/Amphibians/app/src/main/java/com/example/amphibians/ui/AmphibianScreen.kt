package com.example.amphibians.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun AmphibianApp() {

    val viewModel: AmphibianViewModel =
        viewModel(factory = AmphibianViewModel.Factory)

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is AmphibianUiState.Loading -> {
            LoadingScreen()
        }

        is AmphibianUiState.Error -> {
            ErrorScreen()
        }

        is AmphibianUiState.Success -> {
            AmphibianList(
                amphibians =
                    (uiState as AmphibianUiState.Success).amphibians
            )
        }
    }
}

@Composable
fun LoadingScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ErrorScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error al cargar los anfibios",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun AmphibianList(
    amphibians: List<com.example.amphibians.data.Amphibian>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {

        Text(
            text = "Amphibians",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(
                start = 20.dp,
                bottom = 16.dp
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 50.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(amphibians) { amphibian ->

                AmphibianCard(amphibian)
            }
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: com.example.amphibians.data.Amphibian
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column {

            AsyncImage(
                model = amphibian.img_src,
                contentDescription = amphibian.name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = amphibian.name,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = amphibian.type,
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = amphibian.description,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}