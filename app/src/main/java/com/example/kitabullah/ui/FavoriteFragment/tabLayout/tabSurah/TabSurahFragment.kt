package com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabSurah

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.FragmentTabSurahBinding
import com.example.kitabullah.ui.detail.surah.DetailSurahActivity
import com.example.kitabullah.ui.surah.SurahAdapter
import com.example.kitabullah.ui.surah.SurahViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabSurahFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabSurahFragment : Fragment() {
    private var _binding: FragmentTabSurahBinding? = null
    private val binding get() = _binding!!

    private lateinit var surahViewModel: SurahViewModel

    private val surahAdapter = SurahAdapter {
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

        surahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[SurahViewModel::class.java]

        surahViewModel.getSurah()

        surahViewModel.listSurah.observe(requireActivity()) {surah ->
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


}