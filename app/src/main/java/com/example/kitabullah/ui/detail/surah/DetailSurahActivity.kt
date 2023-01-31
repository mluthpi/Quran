package com.example.kitabullah.ui.detail.surah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.R
import com.example.kitabullah.ViewModelFactory
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.data.DetailSuratResponse
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.data.TafsirResponse
import com.example.kitabullah.databinding.ActivityDetailSurahBinding
import com.example.kitabullah.model.QuranEntity


class DetailSurahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailSurahBinding

    private val detailSurahViewModel: DetailSurahViewModel by viewModels {
        ViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val number = intent.getIntExtra("nomor", 0)

        detailSurahViewModel.getDetailSurah(number)
        detailSurahViewModel.listDetailSurah.observe(this, {surahDetail ->
            showSurahDetail(surahDetail)
            println("TEST__ detailSurahViewModel running...")
            Log.d("TEST_DATA", "onCreate: $surahDetail")
        })

        detailSurahViewModel.dataDetail.observe(this, {dataDetail ->
            showLatin(dataDetail)
        })


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

    private fun showLatin(namaLatin: DetailSuratResponse) {
        binding.tvDetailNamaLatin.text = namaLatin.nama
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = namaLatin.namaLatin

        detailSurahViewModel.getFavoriteSurah().observe(this,{favSurah ->
            val isFavorite = favSurah.filter { it.nomor == namaLatin.nomor }.isNotEmpty()
            setupFavoriteSurah(isFavorite, namaLatin)
        })
    }

    private fun setupFavoriteSurah(isFavorite: Boolean, surah : DetailSuratResponse ) {
        if (isFavorite) {
            binding.fbFavorite.setImageResource(R.drawable.ic_baseline_favorited_24)

            binding.fbFavorite.setOnClickListener {
                val title = QuranEntity(
                    nomor = surah.nomor,
                    nama = surah.nama,
                    namaLatin = surah.namaLatin,
                    arti = surah.arti
                )
                detailSurahViewModel.deleteFromDB(title)
                Toast.makeText(this, "Berhasil dihapus dari favorite", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.fbFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

            binding.fbFavorite.setOnClickListener {
                val title = QuranEntity(
                    nomor = surah.nomor,
                    nama = surah.nama,
                    namaLatin = surah.namaLatin,
                    arti = surah.arti
                )
                detailSurahViewModel.insertToDB(title)
                Toast.makeText(this, "Berhasil ditambah ke favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}