package com.example.kitabullah.ui.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.databinding.ListSurahBinding

class SurahAdapter(val onItemClick: (surahItem: SuratResponseItem)-> Unit): RecyclerView.Adapter<SurahAdapter.SurahHolder>() {

    private val surahList = mutableListOf<SuratResponseItem>()

    fun addItems(surahList: List<SuratResponseItem>){
        this.surahList.clear()
        this.surahList.addAll(surahList)
        notifyDataSetChanged()
    }

    class SurahHolder(private val binding: ListSurahBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(surah : SuratResponseItem) {
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