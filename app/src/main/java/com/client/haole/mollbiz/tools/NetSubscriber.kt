package com.client.haole.mollbiz.tools

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.mvp.BaseMvpView
import io.reactivex.subscribers.ResourceSubscriber

abstract class NetSubscriber<T>(val mView: BaseMvpView? = null): ResourceSubscriber<T>() {

    override fun onStart() {
        super.onStart()
        if (!Utils.isNetworkAvailable(App.instance)) {
            mView?.hideProgress()
            onFailed(App.instance.resources.getString(R.string.net_disable))
            dispose()
        }else {
            mView?.showProgress()
        }
    }

    override fun onComplete() {
        mView?.hideProgress()
    }

    override fun onError(t: Throwable?) {
        mView?.hideProgress()
        if (t != null) {
            onFailed(t.message.toString())
        }else {
            onFailed("UNKNOWN_ERROR")
        }

    }

    abstract fun onFailed(message: String)

}