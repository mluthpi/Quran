package com.example.kitabullah.ui.FavoriteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.kitabullah.ViewModelFactory
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.FragmentFavoriteBinding
import com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabSurah.TabSurahFragment
import com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabTafsir.TabTafsirFragment
import com.example.kitabullah.ui.detail.surah.DetailSurahViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteViewModel: FavoriteViewModel

    companion object {
        const val TITLE = "TITLE"

        private val TAB_TITLES = arrayListOf(
            "Surah",
            "Tafsir"
        )
    }

    private lateinit var title : String
    private var isFavorite : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()
        )[FavoriteViewModel::class.java]

        setupTabLayout("title")
    }


    private fun setupTabLayout(title : String) {
        val sectionPagerAdapter = SectionPagerAdapter(requireActivity() as AppCompatActivity, title)
        val viewPager : ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs : TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}