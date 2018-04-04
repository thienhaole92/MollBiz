package com.client.haole.mollbiz.service.impl

import com.client.haole.mollbiz.model.VideoMol
import com.client.haole.mollbiz.service.assist.assist.RetrofitFactory
import com.client.haole.mollbiz.service.assist.assist.RetrofitFactory1
import com.client.haole.mollbiz.tools.NetSubscriber
import com.client.haole.mollbiz.tools.RxHelper
import io.reactivex.disposables.Disposable

class VideoMolApi {

     fun getVideoList(subscriber: NetSubscriber<MutableList<VideoMol>>): Disposable {
         val mNetService = RetrofitFactory1.getInstance().getService()
         mNetService.getVideoList()
                 .compose(RxHelper.listModeThread())
                 .subscribe(subscriber)
        return subscriber
    }

}