package me.project.themovies.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import me.project.themovies.data.MoviesApi
import me.project.themovies.databinding.ActivityMainBinding
import me.project.themovies.domain.Movie
import me.project.themovies.domain.MoviePages
import me.project.themovies.ui.adapter.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesApi: MoviesApi
    private var movieAdapter: MovieAdapter = MovieAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHttclient()
        findMoviesApi()



    }


    private fun setupHttclient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        moviesApi = retrofit.create(MoviesApi::class.java)
    }


    private fun findMoviesApi() {

        moviesApi.getMovies().enqueue(object : Callback<MoviePages> {
            override fun onResponse(call: Call<MoviePages>, response: Response<MoviePages>) {
                if (response.isSuccessful) {
                    val movies: MoviePages = response.body()!!
                    movieAdapter = MovieAdapter(movies.resultado)
                    binding.rvMovies.adapter = movieAdapter
                } else {
                    showErrorMessage()
                }
            }

            override fun onFailure(call: Call<MoviePages>, t: Throwable) {
                showErrorMessage()

            }

        })




    }




    private fun showErrorMessage() {
        Toast.makeText(this, "Erro Inesperado", Toast.LENGTH_SHORT).show()
    }
}