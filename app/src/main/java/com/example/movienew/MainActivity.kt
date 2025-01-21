package com.example.movienew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movienew.data.DataSource
import com.example.movienew.model.Movie
import com.example.movienew.ui.theme.MovieNewTheme
import com.example.movienew.screens.BookmarkScreen
import com.example.movienew.screens.ProfileScreen
import com.example.movienew.screens.SignUpScreen
import com.example.movienew.screens.LoginScreen
import com.example.movienew.screens.SearchScreen
import com.example.movienew.screens.EditProfileScreen







class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieNewTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signup") {
        composable("signup") { SignUpScreen(navController)
        }
        composable("login") { LoginScreen(navController)
        }
        composable("main") { MainScreen(navController)
        }
        composable("profile") { ProfileScreen(navController) // used for the logout to work
        }
        composable("editProfile") { EditProfileScreen(navController)
        }

    }
}






@Composable
fun MainScreen(navController: NavController) {
    var selectedBottomTab by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedBottomTab) {
                selectedBottomTab = it
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedBottomTab) {
                "Home" -> HomeScreen()
                "Bookmark" -> BookmarkScreen()
                "Profile" -> ProfileScreen(navController)
                "Search" -> SearchScreen()
            }
        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5EFE7))
            .padding(12.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(Icons.Default.Home, "Home", selectedTab == "Home") {
            onTabSelected("Home")
        }
        BottomNavItem(Icons.Default.Bookmark, "Bookmark", selectedTab == "Bookmark") {
            onTabSelected("Bookmark")
        }
        BottomNavItem(Icons.Default.Person, "Profile", selectedTab == "Profile") {
            onTabSelected("Profile")
        }
        BottomNavItem(Icons.Default.Search, "Search", selectedTab == "Search") {  // Added search icon
            onTabSelected("Search")
        }
    }
}

// Individual Navigation Item
@Composable
fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color(0xFF1E6CE3) else Color.Gray
        )
        Text(
            text = label,
            color = if (isSelected) Color(0xFF1E6CE3) else Color.Gray
        )
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(180.dp)
    ) {
        // Movie Poster
        Image(
            painter = painterResource(movie.posterResId),
            contentDescription = null,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
        )

        // Movie Title
        Text(
            text = stringResource(movie.titleResId),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
        )

        // Rating Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "★",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFFFD700)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = movie.rating.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


// Home Screen with Top Navigation Bar for Movies/Anime
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf("Movie") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())  // Vertical scroll
            .padding(16.dp)
    ) {
        // App Logo
        Image(
            painter = painterResource(id = R.drawable.movie_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // Top Navigation Bar (Movies/Anime)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            NavigationTab("Movie", selectedTab == "Movie") { selectedTab = "Movie" }
            NavigationTab("Anime", selectedTab == "Anime") { selectedTab = "Anime" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Movie or Anime Section Based on Tab Selection
        if (selectedTab == "Movie") {
            MovieSection()
        } else {
            AnimeSection()
        }
    }
}

@Composable
fun MovieSection() {
    val newMovies = DataSource().loadNewMovies()
    val popularMovies = DataSource().loadPopularMovies()

    // New Movies Section
    Text("New Movies", style = MaterialTheme.typography.headlineSmall,color = Color(0xFF1E6CE3))
    LazyRow {
        items(newMovies) { movie -> MovieCard(movie) }
    }

    Spacer(modifier = Modifier.height(35.dp))

    // Popular Movies Section
    Text("Popular Movies", style = MaterialTheme.typography.headlineSmall,color = Color(0xFF1E6CE3))
    LazyRow {
        items(popularMovies) { movie -> MovieCard(movie) }
    }
}






// Top Navigation Tab
@Composable
fun NavigationTab(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
        if (isSelected) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(40.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun AnimeSection() {
    val newAnimeList = DataSource().loadNewAnime()
    val popularAnimeList = DataSource().loadPopularAnime()

    // New Anime Section
    Text("New Anime", style = MaterialTheme.typography.headlineSmall,color = Color(0xFF1E6CE3))
    LazyRow {
        items(newAnimeList) { anime -> MovieCard(anime) }
    }

    Spacer(modifier = Modifier.height(32.dp))

    // Popular Anime Section
    Text("Popular Anime", style = MaterialTheme.typography.headlineSmall,color = Color(0xFF1E6CE3))
    LazyRow {
        items(popularAnimeList) { anime -> MovieCard(anime) }
    }
}



