package com.example.kitabullah.ui.detail.tafsir

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.data.TafsirItem
import com.example.kitabullah.data.TafsirResponse
import com.example.kitabullah.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTafsirViewModel: ViewModel() {


    private val _detailTafsir = MutableLiveData<List<TafsirItem>>()
    val detailTafsir: LiveData<List<TafsirItem>> = _detailTafsir

    fun getDetailTafsir() {
        val client = ApiConfig.getApiRest().getTafsir()
        client.enqueue(object : Callback<TafsirResponse> {
            override fun onResponse(
                call: Call<TafsirResponse>,
                response: Response<TafsirResponse>
            ) {
                if (response.isSuccessful) {
                    _detailTafsir.value = response.body()?.tafsir?: emptyList()
                } else {
                    Log.e("TEST", "onResponse: onResponse:${response.message()} ")
                }
            }

            override fun onFailure(call: Call<TafsirResponse>, t: Throwable) {
                Log.e("TESTLOG", "onFailure: onFailure: ${t.message.toString()}")
            }
        })
    }

}