package com.example.kitabullah.ui.detail.tafsir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.TafsirItem
import com.example.kitabullah.data.TafsirResponse
import com.example.kitabullah.databinding.ActivityDetailTafsirBinding

class DetailTafsirActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailTafsirBinding
    private lateinit var detailTafsirViewModel: DetailTafsirViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTafsirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val number = intent.getIntExtra("nomor", 0)


        detailTafsirViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        )[DetailTafsirViewModel::class.java]


        detailTafsirViewModel.getDetailTafsir(number)
        detailTafsirViewModel.listDetailTafsir.observe(this, {tafsirDetail ->
            showTafsirDetail(tafsirDetail)
        })

        showLatin(TafsirResponse())
    }



    private fun showTafsirDetail(listTafsirItem: List<TafsirItem>) {
        val detailTafsirAdapter = DetailTafsirAdapter()
        detailTafsirAdapter.addItems(listTafsirItem)
        binding.rvTafsirActivity.apply {
            layoutManager = LinearLayoutManager(
                this@DetailTafsirActivity,
                RecyclerView.VERTICAL, false
            )
            adapter = DetailTafsirAdapter()
        }
    }

    private fun showLatin(namaLatin: TafsirResponse) {
        binding.tvDetailNamaLatin.text = namaLatin.namaLatin
    }
}