package com.example.movienew.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*

import com.example.movienew.data.DataSource
import com.example.movienew.model.Movie
import com.example.movienew.R
import com.example.movienew.ui.theme.Blue
import com.example.movienew.ui.theme.errorLight
import com.example.movienew.ui.theme.lightblack

@Composable
fun BookmarkScreen() {
    val bookmarkedMovies = remember { mutableStateListOf<Movie>().apply { addAll(DataSource().loadBookmarkedMovies()) } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bookmark",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Blue
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (bookmarkedMovies.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No bookmarks yet!",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            } else {
                items(bookmarkedMovies) { movie ->
                    BookmarkMovieCard(movie) {
                        bookmarkedMovies.remove(movie)
                    }
                }
            }
        }
    }
}

@Composable
fun BookmarkMovieCard(movie: Movie, onDelete: () -> Unit) {
    var showMessage by remember { mutableStateOf(false) }
    var isJiggling by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var jiggleJob by remember { mutableStateOf<Job?>(null) } // Track coroutine job

    // Jiggle Animation
    val rotation by animateFloatAsState(
        targetValue = if (isJiggling) 20f else 0f, // Reset animation to 0 when isJiggling is false
        animationSpec = if (isJiggling) {
            infiniteRepeatable(
                animation = tween(90, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        } else {
            tween(90) //  reset to 0
        },
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = lightblack, shape = MaterialTheme.shapes.medium)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = movie.posterResId),
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
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "★",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = movie.rating.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }

        IconButton(
            onClick = {
                isJiggling = true

                jiggleJob = coroutineScope.launch {
                    delay(400) // Jiggle delay
                    isJiggling = false
                    showMessage = true
                }
            },
            modifier = Modifier
                .size(40.dp)
                .rotate(rotation) // call jiggle effect
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete",
                tint = Color.Red
            )
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    if (showMessage) {
        AlertDialog(
            onDismissRequest = {
                showMessage = false
                isJiggling = false
                jiggleJob?.cancel()
            },
            title = { Text(text = "Delete Bookmark") },
            text = { Text(text = "Do you wish to delete this bookmark?", color = errorLight) },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showMessage = false
                    isJiggling = false
                    jiggleJob?.cancel()
                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showMessage = false
                    isJiggling = false
                    jiggleJob?.cancel()
                }) {
                    Text(text = "No")
                }
            }
        )
    }
}








