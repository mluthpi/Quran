package com.example.kitabullah

import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kitabullah.databinding.ActivityMainBinding
import com.example.kitabullah.ui.FavoriteFragment.FavoriteFragment
import com.example.kitabullah.ui.surah.SurahFragment
import com.example.kitabullah.ui.tafsir.TafsirFragment
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory

class MainActivity : AppCompatActivity() {


    private val mOnNavigationOnItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment : Fragment?
            when (item.itemId) {
                R.id.navigation_surah -> {
                    fragment = SurahFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_tafsir -> {
                    fragment = TafsirFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    fragment = FavoriteFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bttmNavigation.setOnNavigationItemSelectedListener(mOnNavigationOnItemSelectedListener)

    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_navigation, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}