package com.example.mvvmroomretrofittutorial

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.mvvmroomretrofittutorial.api.QuotesService
import com.example.mvvmroomretrofittutorial.api.RetrofitHelper
import com.example.mvvmroomretrofittutorial.db.QuotesDatabase
import com.example.mvvmroomretrofittutorial.repo.QuotesRepository
import com.example.mvvmroomretrofittutorial.worker.QuotesWorker
import java.util.concurrent.TimeUnit

class QuotesApplication : Application() {

    lateinit var quotesRepository: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest =
            PeriodicWorkRequest.Builder(QuotesWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialize() {
        var quoteService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        var quotesDatabase = QuotesDatabase.getInstance(applicationContext)
        quotesRepository = QuotesRepository(quoteService, quotesDatabase, applicationContext)
    }
}