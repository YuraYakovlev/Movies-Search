package com.example.moviessearch.ui.main.viewmodel

import android.util.Log
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
    private val log = "Tag"

    fun getMovie() = getDataFromLocalStorage()

    private fun getDataFromLocalStorage() {
        val random = Random(Date().time).nextBoolean()
        localLiveData.value = Loading
        Thread {
            Thread.sleep(1000)
            if (random) {
                localLiveData.postValue(Success(repository.getLocalFromStorage()))
                Log.d(log, "TAG")
            }
            else {
                localLiveData.postValue(MyError(err = Throwable("Failed load")))
                Log.d(log,"!TAG")
            }
        }
            .start()
    }

}