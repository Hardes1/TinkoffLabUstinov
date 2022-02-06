package com.example.tinkofflabustinov.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tinkofflabustinov.UI.top.GifTopFragment
import com.example.tinkofflabustinov.UI.GifType
import com.example.tinkofflabustinov.UI.hot.GifHotFragment
import com.example.tinkofflabustinov.UI.latest.GifLatestFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GifTopFragment()
            1 -> GifLatestFragment()
            2 -> GifHotFragment()
            else -> throw NotImplementedError()
        }
    }
}