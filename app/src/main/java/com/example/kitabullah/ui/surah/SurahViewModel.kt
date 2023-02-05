package com.example.kitabullah.ui.surah

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class SurahViewModel : ViewModel() {

    companion object {
        private const val TAG = "TEST"
    }

    private val _listSurah = MutableLiveData<List<SuratResponseItem>>()
    val listSurah: LiveData<List<SuratResponseItem>> = _listSurah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSurah(surah : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiRest().getSurat(surah)
        client.enqueue(object : Callback<List<SuratResponseItem>>{
            override fun onResponse(call: Call<List<SuratResponseItem>>, response: Response<List<SuratResponseItem>>) {
                _isLoading.value = false
                if(response.isSuccessful) {
                    _listSurah.value = response.body()
                } else {
                    _listSurah.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<SuratResponseItem>>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

}

