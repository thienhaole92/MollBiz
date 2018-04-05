package com.client.haole.mollbiz.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.AboutContract
import com.client.haole.mollbiz.contract.ImageContract
import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.mvp.BaseMvpFragment
import com.client.haole.mollbiz.presenter.AboutPresenter
import com.client.haole.mollbiz.presenter.ImagePresenter

class AboutFragment : BaseMvpFragment<AboutContract.View, AboutPresenter>(), AboutContract.View {

    companion object {

        fun newInstance() : AboutFragment {
            val fragment = AboutFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override var mPresenter = AboutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}
