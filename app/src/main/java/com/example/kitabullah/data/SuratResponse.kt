package com.example.kitabullah.data

import com.google.gson.annotations.SerializedName

//data class SuratResponse(
//
//	@field:SerializedName("SuratResponse")
//	val suratResponse: List<SuratResponseItem?>? = null
//)

data class SuratResponseItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("nama_latin")
	val namaLatin: String? = null,

	@field:SerializedName("jumlah_ayat")
	val jumlahAyat: Int? = null,

	@field:SerializedName("tempat_turun")
	val tempatTurun: String? = null,

	@field:SerializedName("arti")
	val arti: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("nomor")
	val nomor: Int? = null
)
