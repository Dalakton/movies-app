package me.project.themovies.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.project.themovies.data.MoviesApi
import me.project.themovies.databinding.ActivityDetailBinding
import me.project.themovies.model.DetailMovies
import me.project.themovies.model.Movie
import me.project.themovies.repositories.MainRepository
import me.project.themovies.viewModel.DetailViewModel
import me.project.themovies.viewModel.DetailViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    private val moviesApi = MoviesApi.getInstance()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel =
            ViewModelProvider(
                this,
                DetailViewModelFactory(MainRepository(moviesApi))
            )[DetailViewModel::class.java]

        getDetails()
    }

    private fun getDetails() {
        val movie: Movie = intent.getSerializableExtra("movie") as Movie
        viewModel.getAlMovies(movie.id)
        viewModel.movieDetails.observe(this) {
            loadDetail(it)
        }
        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadDetail(moviesDetail: DetailMovies) {
        binding.textMovieName.text = moviesDetail.tituloDoFilme
        binding.textMovieDescription.text = moviesDetail.descricao

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + moviesDetail.imagemDoFilme)
            .centerCrop().into(binding.imageMoviePoster)

        binding.rbMovieStars.rating = (moviesDetail.estrelas/2).toFloat()
    }
}

