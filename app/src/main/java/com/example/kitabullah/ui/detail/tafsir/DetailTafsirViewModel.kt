package com.example.kitabullah.ui.detail.tafsir

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kitabullah.data.AyatItem
import com.example.kitabullah.data.TafsirItem
import com.example.kitabullah.data.TafsirResponse
import com.example.kitabullah.model.QuranEntity
import com.example.kitabullah.network.ApiConfig
import com.example.kitabullah.repository.QuranRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTafsirViewModel(application: Application): ViewModel() {
    private val mTafsirRepository : QuranRepository = QuranRepository(application)


    private val _listDetailTafsir = MutableLiveData<List<TafsirItem>>()
    val listDetailTafsir: LiveData<List<TafsirItem>> = _listDetailTafsir

    private val _dataDetail = MutableLiveData<TafsirResponse>()
    val dataDetail : LiveData<TafsirResponse> = _dataDetail

    fun getDetailTafsir(nomor : Int) {
        val client = ApiConfig.getApiRest().getTafsir(nomor = nomor)
        client.enqueue(object : Callback<TafsirResponse> {
            override fun onResponse(
                call: Call<TafsirResponse>,
                response: Response<TafsirResponse>
            ) {
                if (response.isSuccessful) {
                    _listDetailTafsir.value = response.body()?.tafsir?: emptyList()
                    _dataDetail.value = response.body()
                } else {
                    Log.e("TEST", "onResponse: onResponse:${response.message()} ")
                }
            }

            override fun onFailure(call: Call<TafsirResponse>, t: Throwable) {
                Log.e("TESTLOG", "onFailure: onFailure: ${t.message.toString()}")
            }
        })
    }

    fun insertToDB(tafsir : QuranEntity) {
        mTafsirRepository.insert(tafsir)
    }

    fun deleteFromDB(tafsir : QuranEntity) {
        mTafsirRepository.delete(tafsir)
    }

    fun getFavoriteTafsir() : LiveData<List<QuranEntity>> = mTafsirRepository.getAllQuran()

}