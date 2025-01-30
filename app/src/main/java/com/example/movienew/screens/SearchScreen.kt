package com.example.movienew.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import coil.compose.rememberAsyncImagePainter
import com.example.movienew.data.DataSource
import com.example.movienew.model.Movie
import com.example.movienew.ui.theme.lightblack

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val allMovies = remember {
        DataSource().loadNewMovies() +
                DataSource().loadPopularMovies() +
                DataSource().loadNewAnime() +
                DataSource().loadPopularAnime()
    }

    val moviesWithTitles = allMovies.map { movie ->
        movie to stringResource(id = movie.titleResId)
    }

    val filteredMovies = moviesWithTitles.filter { (_, title) ->
        searchQuery.isEmpty() || title.startsWith(searchQuery, ignoreCase = true)
    }.map { it.first }


    LaunchedEffect(searchQuery) {
        isLoading = true
        delay(500)
        isLoading = false
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search for a Movie or Anime") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp).alpha(0.7f),
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn {
            items(filteredMovies) { movie ->
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = lightblack, shape = MaterialTheme.shapes.medium)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.posterResId),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(movie.titleResId),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "★",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Yellow,
                    modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = movie.rating.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}




