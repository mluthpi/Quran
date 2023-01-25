package com.example.kitabullah.ui.FavoriteFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.ListSurahBinding
import com.example.kitabullah.model.QuranEntity

class FavoriteAdapter(val onItemClick: (surahItem: QuranEntity)-> Unit): RecyclerView.Adapter<FavoriteAdapter.SurahHolder>() {

    private val surahList = mutableListOf<QuranEntity>()

    fun addItems(surahList: List<QuranEntity>){
        this.surahList.clear()
        this.surahList.addAll(surahList)
        notifyDataSetChanged()
    }


    class SurahHolder(private val binding: ListSurahBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(surah : QuranEntity) {
            with(binding) {
                tvNamaSurah.text = surah.namaLatin
                tvArti.text = surah.arti
                tvHijaiyah.text = surah.nama
                tvNumberSurah.text = surah.nomor.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahHolder {
        val surahBinding = ListSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahHolder(surahBinding)
    }

    override fun onBindViewHolder(holder: SurahHolder, position: Int) {
        val surahItem = surahList[position]
        holder.bind(surahItem)
        holder.itemView.setOnClickListener{onItemClick(surahItem)}
    }

    override fun getItemCount(): Int = surahList.size


}