package com.example.mvvmroomretrofittutorial.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmroomretrofittutorial.ToastBarCallBack
import com.example.mvvmroomretrofittutorial.model.QuotesList
import com.example.mvvmroomretrofittutorial.repo.QuotesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesViewModel(private val repository: QuotesRepository) : ViewModel() {

    private var toastBarCallBack: ToastBarCallBack? = null
    val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "$exception v !")
    }

    private val _quotesLiveData = MutableLiveData<QuotesList>()
    val quotesLiveData: LiveData<QuotesList>
        get() = _quotesLiveData


    fun fetchQuotesFromService(page: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val result = repository.getQuotes(page)
            if (result.body() != null && result.isSuccessful) {
                _quotesLiveData.postValue(result.body())
            } else {
                val errorMessage = result.message()
                toastBarCallBack?.showToastBar(errorMessage)
            }
        }
    }

    fun setSnackBarCallBack(callBack: ToastBarCallBack) {
        toastBarCallBack = callBack
    }

    fun removeSnackBarCallBack() {
        toastBarCallBack = null
    }
}