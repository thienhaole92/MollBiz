package com.client.haole.mollbiz.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.VideoMoreContract
import com.client.haole.mollbiz.mvp.BaseMvpActivity
import com.client.haole.mollbiz.presenter.VideoMorePresenter
import com.client.haole.mollbiz.presenter.VideoPresenter
import kotlinx.android.synthetic.main.activity_video_more.*

class VideoMoreActivity : BaseMvpActivity<VideoMoreContract.View, VideoMorePresenter>(), VideoMoreContract.View {

    private var mIsRefresh = false
    override var mPresenter = VideoMorePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_more)
        setupToolbar(toolbar_video_more)
        initView()
    }

    private fun initView() {
        text_view_title.text = intent.getStringExtra("name")
        toolbar_video_more.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
