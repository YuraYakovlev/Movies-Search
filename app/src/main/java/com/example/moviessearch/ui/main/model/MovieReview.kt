package com.example.moviessearch.ui.main.model

import android.os.Parcelable
import com.example.moviessearch.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieReview(
    val title: Movies = getDefaultMovie(),
    val poster: Int = R.drawable.avengers,
    val director: String = "Director"
) : Parcelable

fun getDefaultMovie() = Movies("Avengers", R.drawable.avengers)

fun getMovies() = listOf(
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers)),
    MovieReview(Movies("Avengers", R.drawable.avengers))
)