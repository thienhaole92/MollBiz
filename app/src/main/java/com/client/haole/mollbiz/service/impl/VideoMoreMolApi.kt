package com.client.haole.mollbiz.service.impl

import com.client.haole.mollbiz.model.VideoMoreMol
import com.client.haole.mollbiz.service.assist.assist.RetrofitFactory
import com.client.haole.mollbiz.service.assist.assist.RetrofitFactory1
import com.client.haole.mollbiz.tools.NetSubscriber
import com.client.haole.mollbiz.tools.RxHelper
import io.reactivex.disposables.Disposable

class VideoMoreMolApi {

    fun getVideoMoreList(category: String, strategy: String, udid: String, vc: Int, subscriber: NetSubscriber<VideoMoreMol>): Disposable {
        val mNetService = RetrofitFactory.getInstance().getService()
        mNetService.getVideoMoreList(category, strategy, udid, vc)
                .compose(RxHelper.singleModeThread())
                .subscribe(subscriber)
        return subscriber
    }

    fun getVideoMoreList1(start: Int, num: Int, categoryName: String, strategy: String, subscriber: NetSubscriber<VideoMoreMol>): Disposable {
        val mNetService = RetrofitFactory1.getInstance().getService()
        mNetService.getVideoMoreList1(start, num, categoryName, strategy)
                .compose(RxHelper.singleModeThread())
                .subscribe(subscriber)
        return subscriber
    }

}