package com.example.mvvmroomretrofittutorial

import android.app.Application
import com.example.mvvmroomretrofittutorial.api.QuotesService
import com.example.mvvmroomretrofittutorial.api.RetrofitHelper
import com.example.mvvmroomretrofittutorial.db.QuotesDatabase
import com.example.mvvmroomretrofittutorial.repo.QuotesRepository

class QuotesApplication : Application() {

    lateinit var quotesRepository: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        var quoteService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        var quotesDatabase = QuotesDatabase.getInstance(applicationContext)
        quotesRepository = QuotesRepository(quoteService, quotesDatabase, applicationContext)
    }
}