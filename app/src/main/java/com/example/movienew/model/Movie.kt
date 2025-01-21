package com.example.movienew.model


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @StringRes val titleResId: Int,
    @DrawableRes val posterResId: Int,
    val rating: Double

)
