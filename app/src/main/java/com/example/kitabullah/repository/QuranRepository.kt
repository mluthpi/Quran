package com.example.kitabullah.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kitabullah.database.QuranDao
import com.example.kitabullah.database.QuranRoomDatabase
import com.example.kitabullah.model.QuranEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class QuranRepository(application: Application) {
    private val mQuranDao : QuranDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = QuranRoomDatabase.getDatabase(application)
        mQuranDao = db.quranDao()
    }

    fun getAllQuran() : LiveData<List<QuranEntity>> = mQuranDao.getAllQuran()

    fun insert(quran: QuranEntity) {
        executorService.execute {mQuranDao.insert(quran)}
    }

    fun delete(quran: QuranEntity) {
        executorService.execute {mQuranDao.delete(quran)}
    }
}