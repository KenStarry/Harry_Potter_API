package com.kenstarry.harrypotter.core.data

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import okio.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        try {
            return chain.proceed(request)

        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""

            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout, please check your internet connection."
                }
                is UnknownHostException -> {
                    msg = "Unable to make a connection. Check your internet connection"
                }
                is ConnectionShutdownException -> {
                    msg = "Connection Shut down, please check internet connection"
                }
                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }
                is java.lang.IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }

            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body("{${e}}".toResponseBody(null))
                .build()
        }
    }
}