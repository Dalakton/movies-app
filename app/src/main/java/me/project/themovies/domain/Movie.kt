package me.project.themovies.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val imagemDoFilme : String,
    @SerializedName("title")
    val tituloDoFilme: String,
    @SerializedName("overview")
    val descricao: String,
    @SerializedName("release_date")
    val dataDeLancamento:String,
    @SerializedName("vote_count")
    val estrelas : Int
    ) : Parcelable

