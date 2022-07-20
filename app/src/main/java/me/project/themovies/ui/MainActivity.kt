package me.project.themovies.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import me.project.themovies.data.MoviesApi
import me.project.themovies.databinding.ActivityMainBinding
import me.project.themovies.repositories.MainRepository
import me.project.themovies.adapter.MovieAdapter
import me.project.themovies.viewModel.MainViewModel
import me.project.themovies.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private val moviesApi = MoviesApi.getInstance()

    private var  movieAdapter: MovieAdapter = MovieAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(MainRepository(moviesApi))
            )[MainViewModel::class.java]

        observerlist()
    }

    private fun observerlist() {
        viewModel.movieList.observe(this) {
            movieAdapter = MovieAdapter(it.resultado)
            binding.rvMovies.adapter =  movieAdapter
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}