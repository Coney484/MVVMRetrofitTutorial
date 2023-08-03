package com.example.mvvmroomretrofittutorial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomretrofittutorial.repo.QuotesRepository

class QuotesViewModelFactory(private val repository: QuotesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return QuotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
