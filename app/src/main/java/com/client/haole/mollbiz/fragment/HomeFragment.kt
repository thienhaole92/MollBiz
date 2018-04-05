package com.client.haole.mollbiz.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.activity.DetailsActivity
import com.client.haole.mollbiz.adapter.HomeItemListAdapter
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.contract.HomeContract
import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.mvp.BaseMvpFragment
import com.client.haole.mollbiz.presenter.HomePresenter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    companion object {

        val ANDROID = "Android"

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

    override var mPresenter: HomePresenter = HomePresenter()
    private var mIsRefresh = false
    private var mPage = 1
    private val mPageSize = 10
    private val mLists: MutableList<AndMol> by lazy {
        mutableListOf<AndMol>()
    }
    private var mListAdapter: HomeItemListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        initView()
        getAndroid()
    }

    private fun initView() {
        mListAdapter = HomeItemListAdapter(mLists, {
            val intent = Intent()
            intent.putExtra("url", mLists[it].url)
            intent.setClass(context, DetailsActivity::class.java)
            startActivity(intent)
        })
        val layoutManager = LinearLayoutManager(App.instance, LinearLayoutManager.VERTICAL, false)
        recycler_view_home.layoutManager = layoutManager
        recycler_view_home.adapter = mListAdapter

        refresh_layout_home.setColorSchemeResources(R.color.about)
        refresh_layout_home.setOnRefreshListener(this)
        recycler_view_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.i("App_Debug","onScrollStateChanged---》")
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mLists.size - 1) {
                    if (!mIsRefresh) {
                        mPage++
                        getAndroid()
                    }
                }
            }

        })

    }

    fun getAndroid() {
        mPresenter.getAndroid(ANDROID, mPageSize, mPage)
    }

    override fun getDataFailed(msg: String) {
        showMessage(msg)
        if (mIsRefresh) {
            mIsRefresh = false
            refresh_layout_home.isRefreshing = false
        }
    }

    override fun getDataSuccess(ands: JsonResult<MutableList<AndMol>>) {
        if (mIsRefresh) {
            mIsRefresh = false
            refresh_layout_home.isRefreshing = false
            if (mLists.size > 0) mLists.clear()
        }
        mLists.addAll(ands.results)
        mListAdapter!!.notifyDataSetChanged()
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPage = 1
            getAndroid()
        }
    }
}
