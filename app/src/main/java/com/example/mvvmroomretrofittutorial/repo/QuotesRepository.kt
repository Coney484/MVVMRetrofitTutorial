package com.example.mvvmroomretrofittutorial.repo

import android.content.Context
import com.example.mvvmroomretrofittutorial.api.QuotesService
import com.example.mvvmroomretrofittutorial.db.QuotesDatabase
import com.example.mvvmroomretrofittutorial.model.QuotesList
import com.example.mvvmroomretrofittutorial.utils.QuotesUtils
import retrofit2.Response

class QuotesRepository(
    private val quotesService: QuotesService,
    private val quotesDatabase: QuotesDatabase,
    private val applicationContext: Context
) {

    suspend fun getQuotes(pageNumber: Int): Response<QuotesList> {
        if (QuotesUtils.isNetworkAvailable(applicationContext)) {
            val result = quotesService.getQuotes(pageNumber)
            if (result.body() != null) {
                quotesDatabase.quotesDao().insertQuotes(result.body()!!.results)
            }
            return result
        } else {
            val quotes = quotesDatabase.quotesDao().getAllQuotes()
            val quotesList = QuotesList(1, 1, 1, quotes, 1, 1)
            return quotesList as Response<QuotesList>
        }
    }


    suspend fun getQuotesInBackGround() {
        val randomPageNum = (Math.random() * 7).toInt()
        val result = quotesService.getQuotes(randomPageNum)
        if (result.body() != null) {
            quotesDatabase.quotesDao().insertQuotes(result.body()!!.results)
        }
    }
}