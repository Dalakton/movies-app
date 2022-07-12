package me.project.themovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import me.project.themovies.R
import me.project.themovies.databinding.ActivityDetailBinding
import me.project.themovies.domain.Movie
import me.project.themovies.domain.MoviePages

class DetailActivity : AppCompatActivity() {

    object Extras {

        const val MOVIE = "EXTRA_MOVIE"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDetail()
    }


    fun loadDetail() {
        intent?.extras?.getParcelable<Movie>(Extras.MOVIE)?.let {

            binding.textMovieName.text = it.tituloDoFilme
            binding.textMovieDescription.text = it.descricao
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + it.imagemDoFilme)
                .centerCrop().into(binding.imageMoviePoster)

            binding.rbMovieStars.rating = it.estrelas.toFloat()

        }

    }


}