package com.example.kitabullah.ui.FavoriteFragment.tabLayout.tabTafsir

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
import com.example.kitabullah.databinding.FragmentTabTafsirBinding
import com.example.kitabullah.ui.detail.tafsir.DetailTafsirActivity
import com.example.kitabullah.ui.surah.SurahAdapter
import com.example.kitabullah.ui.tafsir.TafsirViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabTafsirFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabTafsirFragment : Fragment() {
    private var _binding: FragmentTabTafsirBinding? = null
    private val binding get() = _binding!!
    private lateinit var tafsirViewModel: TafsirViewModel

    private val tafsirAdapter = SurahAdapter {
        val intent = Intent(this@TabTafsirFragment.requireContext(), DetailTafsirActivity::class.java)
        intent.putExtra("nomor", it.nomor)
        startActivity(intent)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabTafsirBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tafsirViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[TafsirViewModel::class.java]

        tafsirViewModel.getTafsir()

        tafsirViewModel.listTafsir.observe(requireActivity()) { tafsir ->
            showTafsir(tafsir)
        }

        tafsirViewModel.isLoading.observe(requireActivity()) {isLoading ->
            showLoading(isLoading )
        }
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

}