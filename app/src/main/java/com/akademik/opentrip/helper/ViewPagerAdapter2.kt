package com.akademik.opentrip.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter2(val items: ArrayList<Fragment>, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return items[position]
//    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Fragment {
        return items[position]
    }
}