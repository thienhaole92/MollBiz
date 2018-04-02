package com.client.haole.mollbiz.service.assist.assist

import android.content.Context
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.service.impl.NetService
import com.client.haole.mollbiz.tools.Utils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {

    val API_BASE_URL = "http://gank.io/api/"
    val TIMEOUT: Long = 5

    private var mRetrofit: Retrofit? = null
    private var mNetService: NetService? = null

    companion object {
        fun getInstance(): RetrofitFactory {
            return Single.instance
        }
    }

    private object Single {
        val instance = RetrofitFactory()
    }

    init {
        if (mRetrofit == null) {
            createRetrofit(App.instance)
        }
    }

    fun createRetrofit(context: Context) {
        mRetrofit = Retrofit.Builder().client(constructClient(context)).baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        mNetService = mRetrofit?.create(NetService::class.java)
    }

    private fun constructClient(context: Context): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024
        val file = context.externalCacheDir
        val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(Cache(file, cacheSize))
                .addNetworkInterceptor(getNetworkInterceptor())
                .addInterceptor(getInterceptor())
                .retryOnConnectionFailure(true)
                .build()
        return client
    }

    private fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!Utils.isNetworkAvailable(App.instance)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()

            }
            chain.proceed(request)
        }
    }

    private fun getNetworkInterceptor(): Interceptor {
        return  Interceptor { chain ->
            var request = chain.request()
            var response = chain.proceed(request)
            if (Utils.isNetworkAvailable(App.instance)) {
                val maxAge = 0
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build()
            }else {
                val maxStale: Long = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build()
            }
            response

        }
    }

    fun getService(): NetService = mNetService!!;

}