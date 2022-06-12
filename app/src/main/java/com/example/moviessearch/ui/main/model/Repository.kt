package com.example.moviessearch.ui.main.model

interface Repository {
    fun getLocalFromStorage() : Movies
    fun getServerFromStorage() : Movies
}