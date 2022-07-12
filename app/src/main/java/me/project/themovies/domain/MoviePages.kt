package me.project.themovies.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviePages(
    val page: Int,
    @SerializedName("results")
    val resultado : List<Movie>
) : Parcelable