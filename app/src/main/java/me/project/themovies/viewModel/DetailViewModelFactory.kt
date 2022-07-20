package me.project.themovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.project.themovies.repositories.MainRepository

class DetailViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Viewmodel not found")
        }
    }
}