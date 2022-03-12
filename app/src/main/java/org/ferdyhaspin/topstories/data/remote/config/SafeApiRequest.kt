package org.ferdyhaspin.topstories.data.remote.config

import org.ferdyhaspin.topstories.utils.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>) =
        withContext(Dispatchers.IO) {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let { return@withContext it } ?: throw ApiException("EMPTY BODY")
            } else {
                val error = response.errorBody()?.let {
                    try {
                        val error = async(Dispatchers.IO) { it.string() }
                        val responseError = error.await()
                        try {
                            "\n${JSONObject(responseError).getString("message")}"
                        } catch (e: Exception) {
                            e.printStackTrace()
                            ""
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        ""
                    }
                } ?: ""
                throw ApiException(error)
            }
        }
}