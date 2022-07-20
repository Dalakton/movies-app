package me.project.themovies.repositories

import me.project.themovies.data.MoviesApi
import me.project.themovies.model.DetailMovies

class MainRepository (private val moviesApi: MoviesApi){

    private lateinit var detailMovies: DetailMovies

    // abstraindo a chamada da lista de filmes para o repository.
    // para caso ocorra mudança de biblioteca de requisição web eu sei onde trocar.

    fun getAllMovies() = moviesApi.getMovies()

    fun getAllDetails(movieId : Int) = moviesApi.getmoviesDetail(movieId)
}