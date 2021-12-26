package com.kei.cocoroapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.viewpager.widget.PagerAdapter

class SliderAdapter(private val context: Context,
                    private val layouts: Array<Int>):PagerAdapter() {

    private lateinit var layoutInflatter: LayoutInflater

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflatter = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflatter.inflate(layouts[position], container, false) as View
         container.addView(view)

        return view

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = layouts.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object`as View
        container.removeView(view)
    }
}