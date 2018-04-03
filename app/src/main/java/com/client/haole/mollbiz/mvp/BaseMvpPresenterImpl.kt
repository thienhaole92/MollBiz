package com.client.haole.mollbiz.mvp

import io.reactivex.disposables.CompositeDisposable

open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView : V? = null

    val mSubscription: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    open fun unSubscriber() {
        if (mSubscription.isDisposed) {
            mSubscription.dispose()
        }
    }

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

}