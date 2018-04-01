package com.client.haole.mollbiz.activity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.View
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.MainContract
import com.client.haole.mollbiz.fragment.AboutFragment
import com.client.haole.mollbiz.fragment.HomeFragment
import com.client.haole.mollbiz.fragment.ImageFragment
import com.client.haole.mollbiz.fragment.VideoFragment
import com.client.haole.mollbiz.mvp.BaseMvpActivity
import com.client.haole.mollbiz.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), View.OnClickListener {

    var homeFragment: HomeFragment? = null
    var videoFragment: VideoFragment? = null
    var imageFragment: ImageFragment? = null
    var aboutFragment: AboutFragment? = null

    override var mPresenter: MainContract.Presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomMenu()
        initFragments(savedInstanceState)
    }

    override fun onClick(v: View?) {
        clearBottomMenuState()
        when (v?.id) {
            R.id.radio_button_home -> {
                radio_button_home.isChecked = true
                radio_button_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction()
                        .show(homeFragment)
                        .hide(videoFragment)
                        .hide(imageFragment)
                        .hide(aboutFragment)
                        .commit()
            }
            R.id.radio_button_video -> {
                radio_button_video.isChecked = true
                radio_button_video.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction()
                        .show(videoFragment)
                        .hide(homeFragment)
                        .hide(imageFragment)
                        .hide(aboutFragment)
                        .commit()
            }
            R.id.radio_button_image -> {
                radio_button_image.isChecked = true
                radio_button_image.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction()
                        .show(imageFragment)
                        .hide(homeFragment)
                        .hide(videoFragment)
                        .hide(aboutFragment)
                        .commit()
            }

            R.id.radio_button_about -> {
                radio_button_about.isChecked = true
                radio_button_about.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction()
                        .show(aboutFragment)
                        .hide(homeFragment)
                        .hide(videoFragment)
                        .hide(imageFragment)
                        .commit()
            }
        }
    }

    private fun initBottomMenu() {
        radio_button_home.isChecked = true
        radio_button_home.setTextColor(resources.getColor(R.color.black))
        radio_button_home.setOnClickListener(this)
        radio_button_video.setOnClickListener(this)
        radio_button_image.setOnClickListener(this)
        radio_button_about.setOnClickListener(this)
    }

    private fun clearBottomMenuState() {
        radio_group_root_menu.clearCheck()
        radio_button_home.setTextColor(resources.getColor(R.color.gray))
        radio_button_video.setTextColor(resources.getColor(R.color.gray))
        radio_button_image.setTextColor(resources.getColor(R.color.gray))
        radio_button_about.setTextColor(resources.getColor(R.color.gray))
    }

    private fun initFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val fragments: List<Fragment> = supportFragmentManager.fragments
            for (item in fragments) {
                when (item) {
                    is HomeFragment -> homeFragment = item
                    is VideoFragment -> videoFragment = item
                    is ImageFragment -> imageFragment = item
                    is AboutFragment -> aboutFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            videoFragment = VideoFragment()
            imageFragment = ImageFragment()
            aboutFragment = AboutFragment()

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frame_layout_content, homeFragment)
            fragmentTransaction.add(R.id.frame_layout_content, videoFragment)
            fragmentTransaction.add(R.id.frame_layout_content, imageFragment)
            fragmentTransaction.add(R.id.frame_layout_content, aboutFragment)
            fragmentTransaction.commit()
        }
        supportFragmentManager.beginTransaction()
                .show(homeFragment)
                .hide(videoFragment)
                .hide(imageFragment)
                .hide(aboutFragment)
                .commit()
    }

}
