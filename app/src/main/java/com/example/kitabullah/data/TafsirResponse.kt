package com.example.kitabullah.data

import com.google.gson.annotations.SerializedName

data class TafsirResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("surat_selanjutnya")
	val suratSelanjutnya: SuratSelanjutnya? = null,

	@field:SerializedName("nama_latin")
	val namaLatin: String? = null,

	@field:SerializedName("surat_sebelumnya")
	val suratSebelumnya: SuratSebelumnya? = null,

	@field:SerializedName("tafsir")
	val tafsir: List<TafsirItem?>? = null,

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
	val nomor: Int? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class TafsirItem(

	@field:SerializedName("ayat")
	val ayat: Int? = null,

	@field:SerializedName("tafsir")
	val tafsir: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("surah")
	val surah: Int? = null
)

data class SuratSelanjutnya(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("nama_latin")
	val namaLatin: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

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

data class SuratSebelumnya(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("nama_latin")
	val namaLatin: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

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
