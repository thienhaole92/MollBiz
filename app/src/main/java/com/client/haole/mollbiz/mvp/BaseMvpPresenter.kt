package com.client.haole.mollbiz.mvp

interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view : V)
    fun detachView()

}