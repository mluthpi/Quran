package com.example.kitabullah.network

import com.example.kitabullah.data.DetailSuratResponse
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.data.TafsirItem
import com.example.kitabullah.data.TafsirResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRest {

    @GET("surat")
    fun getSurat(@Query("q")surah : String) : Call<List<SuratResponseItem>>

    @GET("surat/{nomor}")
    fun getDetailSurat(@Path("nomor")nomor : Int) : Call<DetailSuratResponse>

    @GET("tafsir/{nomor}")
    fun getTafsir(@Path("nomor")nomor : Int) : Call<TafsirResponse>

}