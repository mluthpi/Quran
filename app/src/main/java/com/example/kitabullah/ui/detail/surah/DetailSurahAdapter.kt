package com.example.kitabullah.ui.detail.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.data.DetailSuratResponse
import com.example.kitabullah.databinding.ListDetailAyatBinding
import com.example.kitabullah.databinding.ListSurahBinding

class DetailSurahAdapter() : RecyclerView.Adapter<DetailSurahAdapter.DetailSurahHolder>() {

    private val detailSurahList = mutableListOf<AyatItem>()

    fun addItems(detailSurahList: List<AyatItem>) {
        this.detailSurahList.clear()
        this.detailSurahList.addAll(detailSurahList)
        notifyDataSetChanged()
    }

    class DetailSurahHolder(private val binding: ListDetailAyatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(detailSurah: AyatItem) {
            with(binding) {
                tvLatin.text = detailSurah.tr
                tvTerjemahan.text = detailSurah.idn
                tvDetailHijaiyah.text = detailSurah.ar
                tvDetailAyat.text = detailSurah.nomor.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSurahHolder {
        val detailSurahBinding = ListDetailAyatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailSurahHolder(detailSurahBinding)
    }

    override fun onBindViewHolder(holder: DetailSurahHolder, position: Int) {
        val detailSurahItem = detailSurahList[position]
        holder.bind(detailSurahItem)
    }

    override fun getItemCount(): Int = detailSurahList.size

}