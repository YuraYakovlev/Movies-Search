package com.example.moviessearch.ui.main.model

interface Repository {
    fun getLocalFromStorage() : List<MovieReview>
    fun getServerFromStorage() : MovieReview
}