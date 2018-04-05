package com.client.haole.mollbiz.contract

import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.mvp.BaseMvpPresenter
import com.client.haole.mollbiz.mvp.BaseMvpView
import org.jetbrains.annotations.NotNull

object ImageContract {

    interface View: BaseMvpView {

        fun getDataSuccess(ands: JsonResult<MutableList<AndMol>>)

        fun getDataFailed(msg: String)

    }

    interface Presenter: BaseMvpPresenter<View> {

        fun getAndroid(@NotNull type: String, page: Int, pagesize: Int)

    }

}