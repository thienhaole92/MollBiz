package com.client.haole.mollbiz.tools

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm?.activeNetworkInfo
        if (info != null) {
            return info.isAvailable && info.isConnected
        }
        return false
    }
}