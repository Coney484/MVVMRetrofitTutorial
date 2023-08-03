package com.example.mvvmroomretrofittutorial

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomretrofittutorial.api.QuotesService
import com.example.mvvmroomretrofittutorial.api.RetrofitHelper
import com.example.mvvmroomretrofittutorial.databinding.ActivityMainBinding
import com.example.mvvmroomretrofittutorial.repo.QuotesRepository
import com.example.mvvmroomretrofittutorial.viewmodel.QuotesViewModel
import com.example.mvvmroomretrofittutorial.viewmodel.QuotesViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ToastBarCallBack {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: QuotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val quotesService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        val repo = QuotesRepository(quotesService)
        viewmodel =
            ViewModelProvider(this, QuotesViewModelFactory(repo)).get(QuotesViewModel::class.java)
        listeners()
        setupUbservers()
    }

    private fun setupUbservers() {
        viewmodel.quotesLiveData.observe(this) {
            if (it != null) {
                Log.d("Data Fetch Succesful", it.toString())
                Toast.makeText(this, "Data Fetched Successful", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Data Fetched Not Successful", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun listeners() {
        binding.button.setOnClickListener {
            if (isConnectedToInternet()) {
                viewmodel.fetchQuotesFromService(1)

            } else {
                Toast.makeText(this, "No Internet", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun showToastBar(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && actNw.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            )
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}