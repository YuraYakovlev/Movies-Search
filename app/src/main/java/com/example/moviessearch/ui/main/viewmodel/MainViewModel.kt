package com.example.moviessearch.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.ui.main.viewmodel.AppState.*
import com.example.moviessearch.ui.main.model.Repository
import java.util.*
import kotlin.random.Random

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val localLiveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState> get() = localLiveData

    fun getMovie() = getDataFromLocalStorage()

    private fun getDataFromLocalStorage() {
        //val random = Random(Date().time).nextBoolean()
        localLiveData.value = Loading
        Thread {
            Thread.sleep(1000)
            localLiveData.postValue(Success(repository.getLocalFromStorage()))
//            if (random) {
//
//            }
//            else {
//                localLiveData.postValue(MyError(err = Throwable("Failed load")))
//            }
        }
            .start()
    }

}