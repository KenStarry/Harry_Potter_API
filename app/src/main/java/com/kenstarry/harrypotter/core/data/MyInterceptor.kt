package com.kenstarry.harrypotter.core.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import java.net.SocketTimeoutException

class MyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        try {
            val request = chain.request()
            val response = chain.proceed(request)

            Log.d("interceptor", response.message.toString())

            return response
        } catch (e: SocketTimeoutException) {
            throw IOException("Connection timeout", e)
        }
    }
}