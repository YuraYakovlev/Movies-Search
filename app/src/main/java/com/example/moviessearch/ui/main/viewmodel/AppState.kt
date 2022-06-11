package com.example.moviessearch.ui.main.viewmodel

import com.example.moviessearch.ui.main.model.MovieReview

sealed class AppState{
    data class Success(val moviesData: List<MovieReview>): AppState()
    data class MyError(val err: Throwable): AppState()
    object Loading : AppState()
}