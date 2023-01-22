package com.example.kitabullah.ui.detail.tafsir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.R
import com.example.kitabullah.data.TafsirItem
import com.example.kitabullah.data.TafsirResponse
import com.example.kitabullah.databinding.ActivityDetailTafsirBinding
import com.example.kitabullah.model.QuranEntity
import com.example.kitabullah.ui.FavoriteFragment.FavoriteFragment

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
            Log.d("TAG", "onCreate: $tafsirDetail")
            showTafsirDetail(tafsirDetail)
        })
        detailTafsirViewModel.dataDetail.observe(this, {dataDetail ->
            showLatin(dataDetail)
        })



    }





    private fun showTafsirDetail(listTafsirItem: List<TafsirItem>) {
        val detailTafsirAdapter = DetailTafsirAdapter()
        detailTafsirAdapter.addItems(listTafsirItem)
        binding.rvTafsirActivity.apply {
            layoutManager = LinearLayoutManager(
                this@DetailTafsirActivity,
                RecyclerView.VERTICAL, false
            )
            adapter = detailTafsirAdapter
        }
    }

    private fun showLatin(namaLatin: TafsirResponse) {
        binding.tvDetailNamaLatin.text = namaLatin.nama
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = namaLatin.namaLatin

        detailTafsirViewModel.getFavoriteTafsir().observe(this, {favTafsir ->
            val isFavorite = favTafsir.filter { it.nomor == namaLatin.nomor }.isNotEmpty()
            setupFavoriteTafsir(isFavorite, namaLatin)
        })
    }

    private fun setupFavoriteTafsir(isFavorite: Boolean, surah: TafsirResponse) {
        if (isFavorite) {
            binding.fbFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

            binding.fbFavorite.setOnClickListener {
                val title = QuranEntity(
                    nomor = surah.nomor,
                    nama = surah.nama,
                    namaLatin = surah.namaLatin,
                    arti = surah.arti
                )
                detailTafsirViewModel.deleteFromDB(title)
                Toast.makeText(this, "Berhasil dihapus dari favorite", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.fbFavorite.setImageResource(R.drawable.ic_baseline_favorited_24)

            binding.fbFavorite.setOnClickListener {
                val title = QuranEntity (
                    nomor = surah.nomor,
                    nama = surah.nama,
                    namaLatin = surah.namaLatin,
                    arti = surah.arti
                )
                detailTafsirViewModel.insertToDB(title)
                Toast.makeText(this, "Berhasil ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}