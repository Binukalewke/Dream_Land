package com.example.movienew.data

import androidx.compose.ui.res.stringResource
import com.example.movienew.R
import com.example.movienew.model.Movie

class DataSource {

    // New Movies Data
    fun loadNewMovies(): List<Movie> {
        return listOf(
            Movie(
                R.string.movie_monterao,
                R.drawable.movie_poster_1,
                rating = 8.7
            ),
            Movie(
                R.string.movie_hot_frosty,
                R.drawable.movie_poster_2,
                rating = 7.9
            ),
            Movie(
                R.string.movie_guardians_galaxy,
                R.drawable.movie_poster_3,
                rating = 8.0
            )
        )
    }

    // Popular Movies Data
    fun loadPopularMovies(): List<Movie> {
        return listOf(
            Movie(
                R.string.movie_dark_knight,
                R.drawable.movie_poster_11,
                rating = 9.0
            ),
            Movie(
                R.string.movie_inception,
                R.drawable.movie_poster_12,
                rating = 8.8
            ),
            Movie(
                R.string.movie_interstellar,
                R.drawable.movie_poster_13,
                rating = 8.6
            ),
            Movie(
                R.string.movie_black_panther,
                R.drawable.movie_poster_7,
                rating = 7.4
            ),
            Movie(
                R.string.movie_avengers_endgame,
                R.drawable.movie_poster_8,
                rating = 8.4
            )
        )
    }

    // New Anime Data
    fun loadNewAnime(): List<Movie> {
        return listOf(
            Movie(
                R.string.anime_thedemonhunter,
                R.drawable.anime_poster_1,
                rating = 9.2
            ),
            Movie(
                R.string.anime_Tales_of_herding_gods,
                R.drawable.anime_poster_4,
                rating = 9.2
            ),
            Movie(
                R.string.anime_Endless_God_Realm,
                R.drawable.anime_poster_5,
                rating = 9.2
            ),
            Movie(
                R.string.anime_Battle_of_Heavens,
                R.drawable.anime_poster_6,
                rating = 9.2
            )



        )
    }

    // Popular Anime Data
    fun loadPopularAnime(): List<Movie> {
        return listOf(
            Movie(
                R.string.anime_amortalsjourney,
                R.drawable.anime_poster_2,
                rating = 8.5
            ),
            Movie(
                R.string.anime_Xian_Ni,
                R.drawable.anime_poster_7,
                rating = 9.2
            ),
            Movie(
                R.string.anime_Jian_Lai,
                R.drawable.anime_poster_8,
                rating = 9.2
            ),
            Movie(
                R.string.anime_thedemonhunter,
                R.drawable.anime_poster_1,
                rating = 9.2
            ),

        )
    }

    // Load Bookmarked Movies with Ratings
    fun loadBookmarkedMovies(): List<Movie> {
        return listOf(
            Movie(R.string.movie_Arena_Wars, R.drawable.movie_poster_9, 4.8),
            Movie(R.string.movie_Dear_Santa, R.drawable.movie_poster_10, 4.5),
            Movie(R.string.anime_soulland, R.drawable.anime_poster_3, 4.2)
        )

    }


}


