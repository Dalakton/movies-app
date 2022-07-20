package me.project.themovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.project.themovies.model.DetailMovies
import me.project.themovies.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val _moviesIdDetail = MutableLiveData<DetailMovies>()
    val movieDetails: LiveData<DetailMovies> = _moviesIdDetail

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getAlMovies(moviesId: Int) {
        val request = repository.getAllDetails(moviesId)
        request.enqueue(object : Callback<DetailMovies> {
            override fun onResponse(call: Call<DetailMovies>, response: Response<DetailMovies>) {
                _moviesIdDetail.value = response.body()
            }

            override fun onFailure(call: Call<DetailMovies>, t: Throwable) {
                _errorMessage.postValue(t.message)
            }
        })
    }
}
