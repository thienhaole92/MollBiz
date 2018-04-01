package com.client.haole.mollbiz.activity

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.contract.SplashContract
import com.client.haole.mollbiz.mvp.BaseMvpActivity
import com.client.haole.mollbiz.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseMvpActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {

    override var mPresenter: SplashContract.Presenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        val fontType = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        text_view_intro.typeface = fontType
        text_view_app_name.typeface = fontType
        setAnimation()
    }

    private fun setAnimation() {
        val alphaAnimation = AlphaAnimation(0.1f, 1.0f)
        alphaAnimation.duration = 1500
        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1500
        val rotateAnimation = RotateAnimation(0f, 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1500

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(alphaAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(rotateAnimation)
        animationSet.duration = 1500
        image_view_icon_splash.startAnimation(animationSet)
        animationSet.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(var1: Animation) {

            }

            override fun onAnimationEnd(var1: Animation) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(var1: Animation) {

            }
        })

    }

}
