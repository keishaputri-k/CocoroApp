package com.kei.cocoroapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.kei.cocoroapp.R
import com.kei.cocoroapp.adapter.SliderAdapter
import com.kei.cocoroapp.db.AppPrefferences
import kotlinx.android.synthetic.main.activity_onboarding.*
import java.util.*

class OnboardingActivity : AppCompatActivity() {
    private lateinit var sliderAdapter: SliderAdapter
    private var points: Array<TextView?>? = null
    private lateinit var layouts: Array<Int>
    private val sliderChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            TODO("Not yet implemented")
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            TODO("Not yet implemented")
        }

        override fun onPageSelected(position: Int) {
            addBottomPoint(position)
            if (position == layouts.size.minus(1)) {
                tv_skip.show()
                tv_start.show()
                tv_next.hide()
            } else{
                tv_skip.show()
                tv_next.show()
                tv_start.hide()
        }
    }

}

private fun addBottomPoint(lastPosition: Int) {
    points = arrayOfNulls(layouts.size)

    l_dot.removeAllViews()
    for (i in 0 until points!!.size){
        points!![i] = TextView(this)
        points!![i]?.text = getColor(R.color.colorPrimary).toString()
        points!![i]?.textSize = 35f
        points!![i]?.setTextColor(resources.getColor(R.color.colorGray))
        l_dot.addView(points!![i])
    }
    if (points!!.isNotEmpty()){
        points!![lastPosition]?.setTextColor(resources.getColor(R.color.colorPrimary))
    }
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_onboarding)
    init()
    dataSet()
    interactions()
}

    private fun interactions() {
        tv_skip.setOnClickListener{
            navigateToLogin()
        }
        tv_start.setOnClickListener {
            navigateToLogin()
        }
        tv_next.setOnClickListener {
            val currentPage = getCurrentScreen(+1)
            if(currentPage <layouts.size){
                vp_slider.currentItem = currentPage
            }else{
                navigateToLogin()
            }
        }
    }

    private fun getCurrentScreen(i: Int): Int = vp_slider.currentItem.plus(i)

    private fun navigateToLogin() {
        AppPrefferences(this).setFirstLaunch(false)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun dataSet() {
        addBottomPoint(0)
        vp_slider.apply {
            adapter = sliderAdapter
            addOnPageChangeListener(sliderChangeListener)
        }
    }

    private fun init() {
       layouts = arrayOf(
           R.layout.first_on_boarding,
           R.layout.sec_on_boarding,
           R.layout.third_on_boarding
       )
        sliderAdapter = SliderAdapter(this, layouts)
    }
}

private fun View.show() {
    visibility = View.VISIBLE
}

private fun View.hide() {
   visibility = View.GONE
}
