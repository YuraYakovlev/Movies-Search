package com.example.moviessearch.ui.main.viewmodel

import com.example.moviessearch.ui.main.model.Movies

sealed class AppState{
    data class Success(val moviesData: Movies): AppState()
    data class MyError(val err: Throwable): AppState()
    object Loading : AppState()
}