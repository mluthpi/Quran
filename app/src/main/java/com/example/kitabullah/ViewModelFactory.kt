package com.example.kitabullah

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kitabullah.ui.FavoriteFragment.FavoriteViewModel
import com.example.kitabullah.ui.detail.surah.DetailSurahViewModel
import com.example.kitabullah.ui.detail.tafsir.DetailTafsirViewModel
import com.example.kitabullah.ui.surah.SurahViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application) : ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailSurahViewModel::class.java)) {
            return DetailSurahViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(DetailTafsirViewModel::class.java)) {
            return DetailTafsirViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

    }
}