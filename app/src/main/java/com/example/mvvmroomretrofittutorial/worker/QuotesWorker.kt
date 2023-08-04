package com.example.mvvmroomretrofittutorial.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvmroomretrofittutorial.QuotesApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesWorker(private val context: Context, private var params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        Log.d("Rohan", "Worker called")
        val quotesRepository = (context as QuotesApplication).quotesRepository
        CoroutineScope(Dispatchers.IO).launch {
            quotesRepository.getQuotesInBackGround()
        }
        return Result.success()
    }
}