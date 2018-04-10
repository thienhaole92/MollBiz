package com.client.haole.mollbiz.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.activity.VideoMoreActivity
import com.client.haole.mollbiz.adapter.VideoItemListAdapter
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.contract.VideoContract
import com.client.haole.mollbiz.model.VideoMol
import com.client.haole.mollbiz.mvp.BaseMvpFragment
import com.client.haole.mollbiz.presenter.VideoPresenter
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : BaseMvpFragment<VideoContract.View, VideoPresenter>(), VideoContract.View {

    companion object {

        val IOS = "IOS"

        fun newInstance(): VideoFragment {
            val fragment = VideoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

    override var mPresenter: VideoPresenter = VideoPresenter()
    private var mPage = 1
    private var mPageSize = 10
    private var mListAdapter: VideoItemListAdapter? = null
    private val mList: MutableList<VideoMol> by lazy {
        mutableListOf<VideoMol>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
        getVideoList()
    }

    override fun getDataSuccess(ands: MutableList<VideoMol>) {
        mList.clear()
        mList.addAll(ands)
        mListAdapter!!.notifyDataSetChanged()
    }

    override fun getDataFailed(msg: String) {

    }

    private fun initView() {
        mListAdapter = VideoItemListAdapter(mList, {
            val intent = Intent()
            val name = mList[it].name
            intent.putExtra("name", name)
            intent.setClass(context, VideoMoreActivity::class.java)
            startActivity(intent)
        })
        val layoutManager = GridLayoutManager(App.instance, 2)
        recycler_view_video.layoutManager = layoutManager
        recycler_view_video.adapter = mListAdapter
    }

    private fun getVideoList() {
        mPresenter.getVideoList()
    }

}
