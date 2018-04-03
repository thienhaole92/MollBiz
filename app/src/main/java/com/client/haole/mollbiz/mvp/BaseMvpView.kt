package com.client.haole.mollbiz.mvp

import android.content.Context

interface BaseMvpView {

    fun getContext() : Context

    fun showProgress() {}

    fun hideProgress() {}

    fun showError(error: String)

    fun showMessage(message: String)

}