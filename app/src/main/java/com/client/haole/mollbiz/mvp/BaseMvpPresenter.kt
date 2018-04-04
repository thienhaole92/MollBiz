package com.client.haole.mollbiz.mvp

import io.reactivex.disposables.CompositeDisposable

interface BaseMvpPresenter<in V : BaseMvpView> {

    val mSubscription: CompositeDisposable

    fun unSubscriber()

    fun attachView(view : V)

    fun detachView()

}