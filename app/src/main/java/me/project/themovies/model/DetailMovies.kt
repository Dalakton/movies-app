package me.project.themovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovies(
    val id: Int,
    @SerializedName("poster_path")
    val imagemDoFilme : String,
    @SerializedName("title")
    val tituloDoFilme: String,
    @SerializedName("overview")
    val descricao: String,
    @SerializedName("vote_count")
    val estrelas : Int

): Parcelable