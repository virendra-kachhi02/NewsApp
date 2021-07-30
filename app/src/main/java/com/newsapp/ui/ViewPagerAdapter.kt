package com.newsapp.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager,
    private var totalTabs: Int
) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MoviesFragment()
            }
            1 -> {
                FashionFragment()
            }
            2 -> {
                ModelsFragment()
            }
            3 -> {
                LocalFragment()
            }
            else -> MoviesFragment()
        }
    }
}