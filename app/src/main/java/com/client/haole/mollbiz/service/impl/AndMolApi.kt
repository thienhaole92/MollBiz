package com.client.haole.mollbiz.service.impl

import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.service.assist.assist.RetrofitFactory
import com.client.haole.mollbiz.tools.NetSubscriber
import com.client.haole.mollbiz.tools.RxHelper
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull

class AndMolApi {

    fun getAndroid(@NotNull type: String, page: Int, pagesize: Int, subscriber: NetSubscriber<JsonResult<MutableList<AndMol>>>): Disposable {
        val mNetService = RetrofitFactory.getInstance().getService()
        mNetService.getAndroid(type, page, pagesize)
                .compose(RxHelper.singleModeThread())
                .subscribe(subscriber)
        return subscriber
    }

}