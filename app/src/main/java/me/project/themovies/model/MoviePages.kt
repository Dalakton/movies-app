package me.project.themovies.model

import com.google.gson.annotations.SerializedName


data class MoviePages(

    val page: Int,
    @SerializedName("results")
    val resultado : List<Movie>
)