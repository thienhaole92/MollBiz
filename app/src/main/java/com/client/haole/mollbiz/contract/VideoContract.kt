package com.client.haole.mollbiz.contract

import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.model.VideoMol
import com.client.haole.mollbiz.mvp.BaseMvpPresenter
import com.client.haole.mollbiz.mvp.BaseMvpView

object VideoContract {

    interface View : BaseMvpView {

        fun getDataSuccess(ands: MutableList<VideoMol>)

        fun getDataFailed(msg: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getVideoList()
    }
}