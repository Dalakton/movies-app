package me.project.themovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.project.themovies.model.MoviePages
import me.project.themovies.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val _movieList = MutableLiveData<MoviePages>()
    val movieList : LiveData<MoviePages> = _movieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage


    init {
        getAllMovies()
    }

    fun getAllMovies() {
        val request = repository.getAllMovies()
        request.enqueue(object : Callback<MoviePages> {
            override fun onResponse(call: Call<MoviePages>, response: Response<MoviePages>) {
                _movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MoviePages>, t: Throwable) {
                _errorMessage.postValue(t.message)
            }
        })
    }
}
