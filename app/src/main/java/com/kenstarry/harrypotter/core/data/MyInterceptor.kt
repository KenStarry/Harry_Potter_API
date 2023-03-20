package com.kenstarry.harrypotter.core.data

import com.kenstarry.harrypotter.core.presentation.util.Constants
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

        var request = chain.request()

        try {
            var response = chain.proceed(request)
            var tryCount = 0
            val maxTries = 5

            while (!response.isSuccessful && tryCount < maxTries) {
                tryCount++

                Thread.sleep(1000)
                request = request.newBuilder().build()
                response = chain.proceed(request)
            }

            return response

        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""

            when (e) {
                is SocketTimeoutException -> {
                    msg = Constants.SOCKET_TIMEOUT_MSG
                }
                is UnknownHostException -> {
                    msg = Constants.UNKNOWN_HOST_MSG
                }
                is ConnectionShutdownException -> {
                    msg = Constants.CONNECTION_SHUTDOWN_MSG
                }
                is IOException -> {
                    msg = Constants.IO_EXCEPTION_MSG
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