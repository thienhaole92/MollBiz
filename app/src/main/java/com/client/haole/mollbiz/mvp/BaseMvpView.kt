package com.client.haole.mollbiz.mvp

import android.content.Context

interface BaseMvpView {

    fun getContext() : Context

    fun showError(error: String)

    fun showMessage(message: String)

}