package com.example.movienew.data

import com.example.movienew.R
import com.example.movienew.model.Movie

class DataSource {

    fun loadNewMovies(): List<Movie> {
        return listOf(

            Movie(
                R.string.movie_killer_game,
                R.drawable.movie_poster_3,
                rating = 8.0,
                descriptionResId = R.string.desc_killer_game
            ),
            Movie(
                R.string.movie_avengers_endgame,
                R.drawable.movie_poster_8,
                rating = 8.4,
                descriptionResId = R.string.desc_avengers_endgame
            ),
            Movie(
                R.string.movie_hot_frosty,
                R.drawable.movie_poster_2,
                rating = 7.9,
                descriptionResId = R.string.desc_hot_frosty
            ),
            Movie(
                R.string.movie_Sonic_the_Hedgehog_3,
                R.drawable.movie_poster_14,
                rating = 7.0,
                descriptionResId = R.string.desc_Sonic_the_Hedgehog_3
            ),
            Movie(
                R.string.movie_Sentinel,
                R.drawable.movie_poster_15,
                rating = 5.7,
                descriptionResId = R.string.desc_Sentinel
            ),


        )
    }

    fun loadPopularMovies(): List<Movie> {
        return listOf(
            Movie(
                R.string.movie_dark_knight,
                R.drawable.movie_poster_11,
                rating = 9.0,
                descriptionResId = R.string.desc_dark_knight
            ),
            Movie(
                R.string.movie_inception,
                R.drawable.movie_poster_12,
                rating = 8.8,
                descriptionResId = R.string.desc_inception
            ),
            Movie(
                R.string.movie_interstellar,
                R.drawable.movie_poster_13,
                rating = 8.6,
                descriptionResId = R.string.desc_interstellar
            ),
            Movie(
                R.string.movie_black_panther,
                R.drawable.movie_poster_7,
                rating = 7.4,
                descriptionResId = R.string.desc_black_panther
            ),
            Movie(
                R.string.movie_uglies,
                R.drawable.movie_poster_1,
                rating = 8.7,
                descriptionResId = R.string.desc_uglies
            )

        )
    }

    fun loadNewAnime(): List<Movie> {
        return listOf(
            Movie(
                R.string.anime_thedemonhunter,
                R.drawable.anime_poster_1,
                rating = 9.0,
                descriptionResId = R.string.desc_thedemonhunter
            ),
            Movie(
                R.string.anime_Tales_of_herding_gods,
                R.drawable.anime_poster_4,
                rating = 9.2,
                descriptionResId = R.string.desc_tales_of_herding_gods
            ),
            Movie(
                R.string.anime_Endless_God_Realm,
                R.drawable.anime_poster_5,
                rating = 8.1,
                descriptionResId = R.string.desc_endless_god_realm
            ),
            Movie(
                R.string.anime_Battle_of_Heavens,
                R.drawable.anime_poster_6,
                rating = 9.5,
                descriptionResId = R.string.desc_battle_of_heavens
            ),
            Movie(
                R.string.anime_world_of_Immortals,
                R.drawable.anime_poster_10,
                rating = 9.0,
                descriptionResId = R.string.desc_world_of_Immortals
            )
        )
    }

    fun loadPopularAnime(): List<Movie> {
        return listOf(
            Movie(
                R.string.anime_amortalsjourney,
                R.drawable.anime_poster_2,
                rating = 8.5,
                descriptionResId = R.string.desc_amortalsjourney
            ),
            Movie(
                R.string.anime_Xian_Ni,
                R.drawable.anime_poster_7,
                rating = 8.3,
                descriptionResId = R.string.desc_xian_ni
            ),
            Movie(
                R.string.anime_Jian_Lai,
                R.drawable.anime_poster_8,
                rating = 9.2,
                descriptionResId = R.string.desc_jian_lai
            ),
            Movie(
                R.string.anime_Shrouding_The_Heavens,
                R.drawable.anime_poster_9,
                rating = 8.6,
                descriptionResId = R.string.desc_Shrouding_The_Heavens
            ),
            Movie(
                R.string.anime_swallowed_star,
                R.drawable.anime_poster_11,
                rating = 8.9,
                descriptionResId = R.string.desc_swallowed_star
            )

        )
    }

    fun loadBookmarkedMovies(): List<Movie> {
        return listOf(
            Movie(
                R.string.movie_Arena_Wars,
                R.drawable.movie_poster_9,
                rating = 7.8,
                descriptionResId = R.string.desc_arena_wars
            ),
            Movie(
                R.string.movie_Dear_Santa,
                R.drawable.movie_poster_10,
                rating = 6.5,
                descriptionResId = R.string.desc_dear_santa
            ),
            Movie(
                R.string.anime_soulland,
                R.drawable.anime_poster_3,
                rating = 9.0,
                descriptionResId = R.string.desc_soul_land
            )
        )
    }

    fun PosterBanner(): List<Movie> {
        return listOf(
            Movie(
                R.string.movie_Arena_Wars,
                R.drawable.lion2,
                rating = 4.8,
                descriptionResId = R.string.desc_arena_wars
            ),
            Movie(
                R.string.movie_Dear_Santa,
                R.drawable.avatar,
                rating = 4.5,
                descriptionResId = R.string.desc_dear_santa
            ),
            Movie(
                R.string.anime_soulland,
                R.drawable.cars,
                rating = 4.2,
                descriptionResId = R.string.desc_soul_land
            ),
            Movie(
                R.string.anime_soulland,
                R.drawable.inception,
                rating = 4.2,
                descriptionResId = R.string.desc_soul_land
            ),
            Movie(
                R.string.anime_soulland,
                R.drawable.a,
                rating = 4.2,
                descriptionResId = R.string.desc_soul_land
            ),
            Movie(
                R.string.anime_soulland,
                R.drawable.deadpool,
                rating = 4.2,
                descriptionResId = R.string.desc_soul_land
            )



        )
    }
}
