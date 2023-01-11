package com.example.kitabullah.ui.tafsir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TafsirViewModel : ViewModel() {

    companion object {
        private const val TAG = "TEST"
    }

    private val _listTafsir = MutableLiveData<List<SuratResponseItem>>()
    val listTafsir: LiveData<List<SuratResponseItem>> = _listTafsir

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getTafsir() {
        _isLoading.value = true
        val client = ApiConfig.getApiRest().getSurat()
        client.enqueue(object : Callback<List<SuratResponseItem>>{
            override fun onResponse(
                call: Call<List<SuratResponseItem>>,
                response: Response<List<SuratResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listTafsir.value = response.body()
                } else {
                    _listTafsir.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<SuratResponseItem>>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

}