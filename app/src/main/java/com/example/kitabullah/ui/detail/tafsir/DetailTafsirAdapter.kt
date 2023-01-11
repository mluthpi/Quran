package com.example.kitabullah.ui.detail.tafsir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kitabullah.data.TafsirItem
import com.example.kitabullah.databinding.ListDetailTafsirBinding

class DetailTafsirAdapter() : RecyclerView.Adapter<DetailTafsirAdapter.DetailTafsirHolder>() {

    private val detailTafsirList = mutableListOf<TafsirItem>()

    fun addItems(detailTafsirList: List<TafsirItem>) {
        this.detailTafsirList.clear()
        this.detailTafsirList.addAll(detailTafsirList)
        notifyDataSetChanged()
    }

    class DetailTafsirHolder(private val binding: ListDetailTafsirBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(detailTafsir: TafsirItem) {
            with(binding) {
                tvDetailAyat.text = detailTafsir.ayat.toString()
                tvTafsir.text = detailTafsir.tafsir
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTafsirHolder {
        val detailTafsirBinding = ListDetailTafsirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailTafsirHolder(detailTafsirBinding)
    }

    override fun onBindViewHolder(holder: DetailTafsirHolder, position: Int) {
        val detailTafsirItem = detailTafsirList[position]
        holder.bind(detailTafsirItem)
    }

    override fun getItemCount(): Int = detailTafsirList.size
}