package com.bebyaww.hotnewsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bebyaww.hotnewsapp.AboutAlquranFragment
import com.bebyaww.hotnewsapp.AljazeeraFragment
import com.bebyaww.hotnewsapp.CommonFragment
import com.bebyaww.hotnewsapp.WarningFragment

class SectionPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CommonFragment()
            1 -> AboutAlquranFragment()
            2 -> AljazeeraFragment()
            3 -> WarningFragment()
            else -> AljazeeraFragment()
        }
    }

}