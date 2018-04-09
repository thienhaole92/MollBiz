package com.client.haole.mollbiz.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.VideoMoreContract
import com.client.haole.mollbiz.mvp.BaseMvpActivity
import com.client.haole.mollbiz.presenter.VideoMorePresenter
import com.client.haole.mollbiz.presenter.VideoPresenter

class VideoMoreActivity : BaseMvpActivity<VideoMoreContract.View, VideoMorePresenter>(), VideoMoreContract.View {

    override var mPresenter = VideoMorePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_more)
    }
}
