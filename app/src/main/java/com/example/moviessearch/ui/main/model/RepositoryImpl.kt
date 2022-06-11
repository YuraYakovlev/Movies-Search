package com.example.moviessearch.ui.main.model

class RepositoryImpl : Repository {
    override fun getLocalFromStorage() = getMovies()

    override fun getServerFromStorage() = MovieReview()
}