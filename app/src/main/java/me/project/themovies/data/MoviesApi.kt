package me.project.themovies.data

import me.project.themovies.model.DetailMovies
import me.project.themovies.model.MoviePages
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("trending/movie/week?api_key=e249ce4fb8888755f1e8bbd1ad10ed00&page=1&language=pt-BR")
    fun getMovies(): Call<MoviePages>

    @GET("movie/{movie_id}?api_key=e249ce4fb8888755f1e8bbd1ad10ed00&page=1&language=pt-BR")
    fun getmoviesDetail(@Path("movie_id") movieId: Int): Call<DetailMovies>

    companion object {

        private val moviesApi : MoviesApi by lazy{

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

                 retrofit.create(MoviesApi::class.java)

        }

        fun getInstance () : MoviesApi{
            return moviesApi
        }
    }

}