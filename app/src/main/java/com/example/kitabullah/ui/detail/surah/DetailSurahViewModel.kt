package com.example.kitabullah.ui.detail.surah

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.data.DetailSuratResponse
import com.example.kitabullah.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class DetailSurahViewModel : ViewModel() {


    private val _listDetailSurah = MutableLiveData<List<AyatItem>>()
    val listDetailSurah : LiveData<List<AyatItem>> = _listDetailSurah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getDetailSurah(nomor: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiRest().getDetailSurat(nomor = nomor)
        client.enqueue(object : Callback<DetailSuratResponse>{
            override fun onResponse(
                call: Call<DetailSuratResponse>,
                response: Response<DetailSuratResponse>
            ) {
                println("TEST__ getDetailSurah running...")

                _isLoading.value = false
                if(response.isSuccessful) {
                    _listDetailSurah.value = response.body()?.ayat ?: emptyList()
                    println("TEST__ _listDetailSurah.value running...")
                } else {
                    Log.e(TAG, "onResponse: ${response.message()} ")
                }
            }

            override fun onFailure(call: Call<DetailSuratResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}