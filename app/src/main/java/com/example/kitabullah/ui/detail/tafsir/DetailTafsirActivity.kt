package com.example.kitabullah.ui.detail.tafsir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.kitabullah.R
import com.example.kitabullah.databinding.ActivityDetailTafsirBinding

class DetailTafsirActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailTafsirBinding
    private lateinit var detailTafsirViewModel: DetailTafsirViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTafsirBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val tvNamaLatin : TextView = findViewById(R.id.tv_detail_nama_latin)
//        binding.tvDetailNamaLatin.text = tvNamaLatin.
    }
}