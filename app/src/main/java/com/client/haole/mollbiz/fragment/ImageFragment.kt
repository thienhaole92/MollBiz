package com.client.haole.mollbiz.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.adapter.ImageItemListAdapter
import com.client.haole.mollbiz.contract.ImageContract
import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.mvp.BaseMvpFragment
import com.client.haole.mollbiz.presenter.ImagePresenter
import kotlinx.android.synthetic.main.fragment_image.*


class ImageFragment : BaseMvpFragment<ImageContract.View, ImagePresenter>(), ImageContract.View,
SwipeRefreshLayout.OnRefreshListener {


    companion object {

        val GIRL = "福利"

        fun newInstance() : ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override var mPresenter = ImagePresenter()
    private var mPage = 1
    private val mPageSize = 20
    private var mIsRefresh = false
    private val mLists: MutableList<AndMol> by lazy {
        mutableListOf<AndMol>()
    }
    private var mListAdapter: ImageItemListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getImages()
    }

    override fun getDataSuccess(ands: JsonResult<MutableList<AndMol>>) {
        if (mIsRefresh) {
            mIsRefresh = false
            refresh_layout_image.isRefreshing = false
            if (mLists.size > 0) {
                mLists.clear()
            }
        }
        mLists.addAll(ands.results)
        mListAdapter!!.notifyDataSetChanged()
    }

    override fun getDataFailed(msg: String) {

    }

    private fun initView() {
        mListAdapter = ImageItemListAdapter(mLists, {

        })
        val layoutManager = GridLayoutManager(activity, 2)
        recycler_view_image.layoutManager = layoutManager
        recycler_view_image.adapter = mListAdapter

        refresh_layout_image.setColorSchemeResources(R.color.about)
        refresh_layout_image.setOnRefreshListener(this)
        recycler_view_image.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mLists.size - 1) {
                    if (!mIsRefresh) {
                        mPage++
                        getImages()
                    }
                }
            }
        })
    }

    private fun getImages() {
        mPresenter.getAndroid(GIRL, mPageSize, mPage)
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPage = 1
            getImages()
        }
    }

}
