package com.example.kitabullah.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kitabullah.database.QuranDao
import com.example.kitabullah.database.QuranRoomDatabase
import com.example.kitabullah.model.TafsirEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TafsirRepository(application: Application) {
    private val mQuranDao : QuranDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = QuranRoomDatabase.getDatabase(application)
        mQuranDao = db.quranDao()
    }

    fun getAllTafsir() : LiveData<List<TafsirEntity>> = mQuranDao.getAllTafsir()

    fun insert(tafsir: TafsirEntity) {
        executorService.execute {mQuranDao.insert(tafsir)}
    }

    fun delete(tafsir: TafsirEntity) {
        executorService.execute {mQuranDao.delete(tafsir)}
    }

}