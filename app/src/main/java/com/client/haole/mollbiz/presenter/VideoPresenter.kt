package com.client.haole.mollbiz.presenter

import com.client.haole.mollbiz.contract.VideoContract
import com.client.haole.mollbiz.model.VideoMol
import com.client.haole.mollbiz.mvp.BaseMvpPresenterImpl
import com.client.haole.mollbiz.service.impl.VideoMolApi
import com.client.haole.mollbiz.tools.NetSubscriber

class VideoPresenter: BaseMvpPresenterImpl<VideoContract.View>(), VideoContract.Presenter {

    private val mVideoMolApi: VideoMolApi by lazy {
        VideoMolApi()
    }

    override fun getVideoList() {
        val subscriber = mVideoMolApi.getVideoList(object: NetSubscriber<MutableList<VideoMol>>(mView) {
            override fun onNext(t: MutableList<VideoMol>?) {
                mView?.getDataSuccess(t!!)
            }

            override fun onFailed(message: String) {
                mView?.getDataFailed(message)
            }
        })
        mSubscription.add(subscriber)
    }

}