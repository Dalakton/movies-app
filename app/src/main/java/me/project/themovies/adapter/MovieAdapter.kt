package me.project.themovies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.project.themovies.databinding.ItemMoviesBinding
import me.project.themovies.model.Movie
import me.project.themovies.ui.DetailActivity
 class MovieAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemMoviesBinding.inflate(layout, parent, false)
        return ViewHolder( binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = movies[position]

            holder.binding.textMovieName.text = movie.tituloDoFilme
            holder.binding.textDataDeLanAmento.text = movie.dataDeLancamento
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movie.imagemDoFilme)
                .centerCrop().into(holder.binding.imageMovie)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("movie", movie)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return  movies.size
    }

    class ViewHolder(val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)
}