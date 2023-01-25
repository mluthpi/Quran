package com.example.kitabullah.ui.FavoriteFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kitabullah.data.SuratResponseItem
import com.example.kitabullah.model.QuranEntity
import com.example.kitabullah.network.ApiConfig
import com.example.kitabullah.repository.QuranRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val quranRepository = QuranRepository(application)

    fun getFavoriteQuran() : LiveData<List<QuranEntity>> = quranRepository.getAllQuran()
}