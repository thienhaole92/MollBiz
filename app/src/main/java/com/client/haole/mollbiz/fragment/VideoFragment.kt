package com.client.haole.mollbiz.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.VideoContract
import com.client.haole.mollbiz.mvp.BaseMvpFragment
import com.client.haole.mollbiz.presenter.VideoPresenter


class VideoFragment : BaseMvpFragment<VideoContract.View, VideoContract.Presenter>(), VideoContract.View {

    companion object {
        fun newInstance(): VideoFragment {
            val fragment = VideoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override var mPresenter: VideoContract.Presenter = VideoPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

}
