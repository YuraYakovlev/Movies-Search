package com.example.moviessearch

import com.example.moviessearch.ui.main.viewmodel.MainViewModel
import com.example.moviessearch.ui.main.model.Repository
import com.example.moviessearch.ui.main.model.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
    viewModel { MainViewModel(get()) }
}
