package com.example.moviessearch.ui.main.model

class RepositoryImpl : Repository {
    override fun getLocalFromStorage() = Movies()

    override fun getServerFromStorage() = Movies()
}