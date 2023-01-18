package com.example.kitabullah.ui.FavoriteFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabSurah.TabSurahFragment
import com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabTafsir.TabTafsirFragment

class SectionPagerAdapter(activity: AppCompatActivity, val title : String) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null

        val bundle = Bundle()
        val tabSurahFragment = TabSurahFragment()
        val tabTafsirFragment = TabTafsirFragment()

        bundle.putString("TITLE", title)

        tabSurahFragment.arguments = bundle
        tabTafsirFragment.arguments = bundle


        when(position) {
            0 -> fragment = tabSurahFragment
            1 -> fragment = tabTafsirFragment
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }


}