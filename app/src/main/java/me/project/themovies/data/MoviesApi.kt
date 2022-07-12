package me.project.themovies.data

import me.project.themovies.domain.Movie
import me.project.themovies.domain.MoviePages
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {

    @GET("trending/movie/week?api_key=e249ce4fb8888755f1e8bbd1ad10ed00")
    fun getMovies(): Call<MoviePages>

}