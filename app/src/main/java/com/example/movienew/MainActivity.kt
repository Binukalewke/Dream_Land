package com.example.movienew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movienew.data.DataSource
import com.example.movienew.model.Movie
import com.example.movienew.ui.theme.MovieNewTheme
import com.example.movienew.screens.BookmarkScreen
import com.example.movienew.screens.ProfileScreen
import com.example.movienew.screens.SignUpScreen
import com.example.movienew.screens.LoginScreen
import com.example.movienew.screens.SearchScreen
import com.example.movienew.screens.EditProfileScreen
import com.example.movienew.screens.ViewScreen
import com.example.movienew.ui.theme.Blue
import com.example.movienew.ui.theme.beige
import com.example.movienew.ui.theme.staryellow



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
        composable("signup") { SignUpScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("editProfile") { EditProfileScreen(navController) }

        // Movie Details Screen
        composable("movieDetails/{movieTitle}/{moviePoster}/{movieRating}/{movieDescription}",
            arguments = listOf(
                navArgument("movieTitle") { type = NavType.IntType },
                navArgument("moviePoster") { type = NavType.IntType },
                navArgument("movieRating") { type = NavType.StringType },
                navArgument("movieDescription") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getInt("movieTitle") ?: 0
            val moviePoster = backStackEntry.arguments?.getInt("moviePoster") ?: 0
            val movieRating = backStackEntry.arguments?.getString("movieRating")?.toDouble() ?: 0.0
            val movieDescription = backStackEntry.arguments?.getInt("movieDescription") ?: 0

            ViewScreen(movieTitle, moviePoster, movieRating, movieDescription,navController = navController)
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
            Crossfade(
                targetState = selectedBottomTab,
                animationSpec = tween(300)
            ) { screen ->
                when (screen) {
                    "Home" -> HomeScreen(navController)
                    "Bookmark" -> BookmarkScreen()
                    "Profile" -> ProfileScreen(navController)
                    "Search" -> SearchScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = beige)
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
        BottomNavItem(Icons.Default.Search, "Search", selectedTab == "Search") {
            onTabSelected("Search")
        }
    }
}

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
            tint = if (isSelected) Blue else Color.Gray
        )
        Text(
            text = label,
            color = if (isSelected) Blue else Color.Gray
        )
    }
}






@Composable
fun MovieCard(movie: Movie, navController: NavController? = null) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(160.dp)
            .clickable {
                navController?.navigate(
                    "movieDetails/${movie.titleResId}/${movie.posterResId}/${movie.rating}/${movie.descriptionResId}"
                )
            },
        elevation = CardDefaults.cardElevation(8.dp),

        ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(movie.posterResId),
                contentDescription = stringResource(movie.titleResId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(movie.titleResId),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp,2.dp)
                    .fillMaxWidth()


            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp,2.dp)

            ) {
                Text(
                    text = "★",
                    style = MaterialTheme.typography.bodyMedium,
                    color = staryellow,
                    fontSize = 20.sp,
                    modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${movie.rating}",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                    fontSize = 18.sp,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }
    }
}

@Composable
fun NavigationTab(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge,color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
        if (isSelected) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(40.dp)
                    .background(Color.Red)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}









@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Movie") }
    val movieDataSource = DataSource()
    val bannerMovies = movieDataSource.PosterBanner()

    // Track the current banner image
    var currentBannerIndex by remember { mutableStateOf(0) }

    // Change image every 5 seconds
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(5000L)// 5sec
            currentBannerIndex = (currentBannerIndex + 1) % bannerMovies.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.movie_logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Dream Land",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Blue
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = bannerMovies[currentBannerIndex].posterResId),
                    contentDescription = "Movie Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                NavigationTab("Movie", selectedTab == "Movie") { selectedTab = "Movie" }
                NavigationTab("Anime", selectedTab == "Anime") { selectedTab = "Anime" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedTab == "Movie") {
                MovieSection(navController)
            } else {
                AnimeSection(navController)
            }
        }
    }
}





@Composable
fun MovieSection(navController: NavController) {
    val newMovies = DataSource().loadNewMovies()
    val popularMovies = DataSource().loadPopularMovies()

    Text(
        text = "New Movies",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(newMovies) { movie ->
            MovieCard(movie, navController)
        }
    }

    Spacer(modifier = Modifier.height(35.dp))

    Text(
        text = "Popular Movies",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(popularMovies) { movie ->
            MovieCard(movie, navController)
        }
    }
}

@Composable
fun AnimeSection(navController: NavController) {
    val newAnimeList = DataSource().loadNewAnime()
    val popularAnimeList = DataSource().loadPopularAnime()

    Text(
        text = "New Anime",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(newAnimeList) { anime ->
            MovieCard(anime, navController)
        }
    }

    Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = "Popular Anime",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(popularAnimeList) { anime ->
            MovieCard(anime, navController)
        }
    }
}

