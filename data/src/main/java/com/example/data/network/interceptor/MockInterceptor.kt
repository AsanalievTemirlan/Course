package com.example.data.network.interceptor

import android.content.Context
import com.example.data.R
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        return when (path) {
            "/courses" -> {
                val json = context.resources
                    .openRawResource(R.raw.courses)
                    .bufferedReader()
                    .use { it.readText() }

                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .body(json.toByteArray().toResponseBody("application/json".toMediaType()))
                    .addHeader("content-type", "application/json")
                    .build()
            }

            else -> {
                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(404)
                    .message("Not Found")
                    .body("""{"message":"Not Found"}"""
                        .toByteArray()
                        .toResponseBody("application/json".toMediaType()))
                    .build()
            }
        }
    }
}
