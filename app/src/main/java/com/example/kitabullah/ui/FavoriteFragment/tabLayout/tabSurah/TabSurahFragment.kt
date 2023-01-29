package com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabSurah

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.ViewModelFactory
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.FragmentTabSurahBinding
import com.example.kitabullah.model.QuranEntity
import com.example.kitabullah.ui.FavoriteFragment.FavoriteAdapter
import com.example.kitabullah.ui.FavoriteFragment.FavoriteViewModel
import com.example.kitabullah.ui.detail.surah.DetailSurahActivity
import com.example.kitabullah.ui.surah.SurahAdapter
import com.example.kitabullah.ui.surah.SurahViewModel


class TabSurahFragment : Fragment() {
    private var _binding: FragmentTabSurahBinding? = null
    private val binding get() = _binding!!

    private val surahViewModel: FavoriteViewModel by viewModels {
        ViewModelFactory(mApplication = requireActivity().application)
    }

    private val surahAdapter = FavoriteAdapter {
        val intent = Intent(this@TabSurahFragment.requireContext(), DetailSurahActivity::class.java)
        intent.putExtra("nomor", it.nomor)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        surahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
//        )[FavoriteViewModel::class.java]

        surahViewModel.getFavoriteQuran().observe(viewLifecycleOwner, {
            showSurah(it)
        })
        //buat file adapter dgn quranentiti
        //copy dari surahadapter

    }

    private fun showSurah(listSurah: List<QuranEntity>) {
        surahAdapter.addItems(listSurah)
        binding.rvSurah.apply {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                RecyclerView.VERTICAL, false
            )
            adapter = surahAdapter
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}