package com.example.mvvmroomretrofittutorial.api

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class RetryInterceptor(private val maxRetry: Int) : Interceptor {

    private var retryCount: Int = 0

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)

        while (!response.isSuccessful && retryCount < maxRetry) {
            retryCount++

            // Retry the request
            response.close()
            response = chain.proceed(request)
        }

        return response
    }
}
