package com.example.moviessearch.ui.main.model

import android.os.Parcelable
import com.example.moviessearch.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
     val title:String,
     val poster: Int
) : Parcelable
