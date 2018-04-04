package com.client.haole.mollbiz.application

import android.app.Application
import com.client.haole.mollbiz.tools.NotNullSingleValueVar

class App: Application() {

    companion object {

        var instance: App by NotNullSingleValueVar.DelegatesExt.notNullSingleValue()

    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        instance = this
    }


}