package com.client.haole.mollbiz.tools

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.mvp.BaseMvpView
import io.reactivex.subscribers.ResourceSubscriber

abstract class NetSubscriber<T>(val view: BaseMvpView? = null): ResourceSubscriber<T>() {

    override fun onStart() {
        super.onStart()
        if (!Utils.isNetworkAvailable(App.instance)) {
            view?.hideProgress()
            onFailed(App.instance.resources.getString(R.string.net_disable))
            dispose()
        }else {
            view?.showProgress()
        }
    }

    override fun onComplete() {
        view?.hideProgress()
    }

    override fun onError(t: Throwable?) {
        view?.hideProgress()
        if (t != null) {
            onFailed(t.message.toString())
        }else {
            onFailed("UNKNOWN_ERROR")
        }

    }

    abstract fun onFailed(message: String)

}