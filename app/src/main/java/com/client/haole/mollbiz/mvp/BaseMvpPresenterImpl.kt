package com.client.haole.mollbiz.mvp

open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView : V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

}