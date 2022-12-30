package com.example.kitabullah.ui.surah

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.FragmentSurahBinding
import com.example.kitabullah.ui.detail.DetailSurahActivity
import kotlin.math.log

class SurahFragment : Fragment() {

    private var _binding: FragmentSurahBinding? = null
    private val binding get() = _binding!!

    private lateinit var surahViewModel: SurahViewModel

    private val surahAdapter = SurahAdapter {
        val intent = Intent(this@SurahFragment.requireContext(), DetailSurahActivity::class.java)
        intent.putExtra("nomor", it.nomor)
        startActivity(intent)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        surahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[SurahViewModel::class.java]

        surahViewModel.getSurah()


        surahViewModel.listSurah.observe(requireActivity()) {surah ->
            Log.d("TEST_", "onViewCreated: ${surah}")
            showSurah(surah)


        }

        surahViewModel.isLoading.observe(requireActivity()) {isLoading ->
            showLoading(isLoading)
        }

    }

    private fun showSurah(listSurah: List<SuratResponseItem>) {
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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}