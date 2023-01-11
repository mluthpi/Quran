package com.example.kitabullah.ui.detail.surah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.databinding.ActivityDetailSurahBinding

class DetailSurahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailSurahBinding
    private lateinit var detailSurahViewModel: DetailSurahViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val number = intent.getIntExtra("nomor", 0)
        detailSurahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[DetailSurahViewModel::class.java]

        detailSurahViewModel.getDetailSurah(number)

        detailSurahViewModel.listDetailSurah.observe(this, {surahDetail ->
            showSurahDetail(surahDetail)
        })


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun showSurahDetail(listDetailSurah: List<AyatItem>) {
        val detailAdapter = DetailSurahAdapter()
        detailAdapter.addItems(listDetailSurah)
        binding.rvDetailActivity.apply {
            layoutManager = LinearLayoutManager(
                this@DetailSurahActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = detailAdapter
        }

    }

}