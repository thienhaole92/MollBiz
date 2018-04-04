package com.client.haole.mollbiz.presenter

import com.client.haole.mollbiz.contract.HomeContract
import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.mvp.BaseMvpPresenterImpl
import com.client.haole.mollbiz.service.impl.AndMolApi
import com.client.haole.mollbiz.tools.NetSubscriber

class HomePresenter : BaseMvpPresenterImpl<HomeContract.View>(), HomeContract.Presenter {

    private val mAndMolApi: AndMolApi by lazy {
        AndMolApi()
    }

    fun test() {
        mView?.getDataFailed("")
    }

    override fun getAndroid(type: String, page: Int, pagesize: Int) {
        val subscriber = mAndMolApi.getAndroid(type, page, pagesize, object : NetSubscriber<JsonResult<MutableList<AndMol>>>(mView) {
            override fun onNext(t: JsonResult<MutableList<AndMol>>) {
                mView?.getDataSuccess(t);
            }

            override fun onFailed(msg: String) {
                mView?.getDataFailed(msg)
            }
        })
        mSubscription.add(subscriber)
    }

}