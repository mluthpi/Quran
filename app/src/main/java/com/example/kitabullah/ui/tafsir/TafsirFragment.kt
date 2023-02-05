package com.example.kitabullah.ui.tafsir

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.FragmentTafsirBinding
import com.example.kitabullah.ui.detail.tafsir.DetailTafsirActivity
import com.example.kitabullah.ui.surah.SurahAdapter

class TafsirFragment : Fragment() {

    private var _binding: FragmentTafsirBinding? = null
    private val binding get() = _binding!!
    private lateinit var tafsirViewModel: TafsirViewModel

    private val tafsirAdapter = SurahAdapter {
        val intent = Intent(this@TafsirFragment.requireContext(), DetailTafsirActivity::class.java)
        intent.putExtra("nomor",it.nomor)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTafsirBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tafsirViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[TafsirViewModel::class.java]

        tafsirViewModel.getTafsir(surah = String())

        tafsirViewModel.listTafsir.observe(requireActivity()) {tafsir ->
            showTafsir(tafsir)
        }

        tafsirViewModel.isLoading.observe(requireActivity()) {isLoading ->
        showLoading(isLoading )}
    }

    private fun showTafsir(listTafsir: List<SuratResponseItem>) {
        tafsirAdapter.addItems(listTafsir)
        binding.rvTafsir.apply {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                RecyclerView.VERTICAL, false
            )
            adapter = tafsirAdapter
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