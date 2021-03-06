package com.client.haole.mollbiz.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.DetailsContract
import com.client.haole.mollbiz.mvp.BaseMvpActivity
import com.client.haole.mollbiz.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseMvpActivity<DetailsContract.View, DetailsPresenter>(), DetailsContract.View {

    override var mPresenter = DetailsPresenter()

    private var mUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupToolbar(tool_bar_details)
        initView()
    }

    private fun initView() {
        text_view_title.text = "Details"
        tool_bar_details.setNavigationOnClickListener {
            finish()
        }
        mUrl = intent.getStringExtra("url")

        progress_web_view.loadUrl(mUrl)
        progress_web_view.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(mUrl)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

        }
        image_view_icon_share.setOnClickListener {

        }
    }

    override fun onBackPressed() {
        finish()
    }

}
