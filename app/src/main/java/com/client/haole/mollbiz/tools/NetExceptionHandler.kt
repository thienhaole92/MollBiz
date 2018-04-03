package com.client.haole.mollbiz.tools

import android.net.ParseException
import com.google.gson.JsonParseException
import io.reactivex.Flowable
import io.reactivex.functions.Function
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class NetExceptionHandler {

    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504

    fun handleException(t: Throwable): ResponseException {
        val exception = ResponseException(t)
        if (t is HttpException) {
            exception.message = when (t.code()) {
                UNAUTHORIZED -> "Our visit was rejected by the server (${t.code()})"
                FORBIDDEN -> "Server resource unavailable (${t.code()})"
                NOT_FOUND -> "We seem to be lost and can't find the server (${t.code()})"
                REQUEST_TIMEOUT -> "Oops, our request timed out. Please check the network connection and try again (${t.code()})"
                GATEWAY_TIMEOUT -> "Oops, our request timed out. Please check the network connection and try again (${t.code()})"
                INTERNAL_SERVER_ERROR -> "The server is in gaps, please try again later (${t.code()})"
                BAD_GATEWAY -> "The server is going away. Please try again later (${t.code()})"
                SERVICE_UNAVAILABLE -> "The server may be maintaining, please try again later (${t.code()})"
                else -> "The network is abnormal. Please check the network connection and try again (${t.code()})"
            }
        }else if (t is JsonParseException || t is JSONException || t is ParseException) {
            exception.message = "Data parsing error (${ERROR.PARSE_ERROR})"
        } else if (t is ConnectException) {
            exception.message = "Connection failed, network connection may be abnormal, please check the network and try again (${ERROR.NETWORK_ERROR})"
        } else if (t is SSLHandshakeException) {
            exception.message = "Certificate verification failed (${ERROR.SSL_ERROR})"
        } else if (t is UnknownHostException) {
            exception.message = "Could not connect to the server, please check your network or try again later (${ERROR.HOST_ERROR})"
        } else {
            exception.message = "Unknown error (${ERROR.UNKNOWN})"
        }
        return exception
    }

    internal object ERROR {
        const val UNKNOWN = 1000
        const val PARSE_ERROR = 1001
        const val NETWORK_ERROR = 1002
        const val HTTP_ERROR = 1003
        const val SSL_ERROR = 1005
        const val HOST_ERROR = 1006
    }

    class ResponseException(throwable: Throwable) : Exception(throwable) {
        override var message: String? = null
    }

    open class HttpResponseFunc<T>: Function<Throwable, Flowable<T>> {
        override fun apply(t: Throwable): Flowable<T> {
            return Flowable.error(NetExceptionHandler().handleException(t))
        }
    }

}