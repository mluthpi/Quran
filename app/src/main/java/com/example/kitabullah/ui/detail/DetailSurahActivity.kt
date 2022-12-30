package com.example.kitabullah.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.data.DetailSuratResponse
import com.example.kitabullah.databinding.ActivityDetailSurahBinding

class DetailSurahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailSurahBinding
    private lateinit var detailSurahViewModel: DetailSurahViewModel

    private lateinit var adapter : DetailSurahAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val number = intent.getIntExtra("nomor", 0)

        detailSurahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[DetailSurahViewModel::class.java]

        detailSurahViewModel.listDetailSurah.observe(this, {surahDetail ->
            showSurahDetail(surahDetail)
        })
    }

    private fun showSurahDetail(listDetailSurah: AyatItem) {
        adapter.addItems(listDetailSurah)
        binding.rvDetailActivity.apply {
            layoutManager = LinearLayoutManager(
                this@DetailSurahActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = adapter
        }

    }

}