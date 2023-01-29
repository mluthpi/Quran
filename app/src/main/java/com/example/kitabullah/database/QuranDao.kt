package com.example.kitabullah.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kitabullah.model.QuranEntity
import com.example.kitabullah.model.TafsirEntity


@Dao
interface QuranDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (quran: QuranEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tafsir: TafsirEntity)

    @Delete
    fun delete (quran: QuranEntity)

    @Delete
    fun delete (quran: TafsirEntity)

    @Query("SELECT* from quranentity ORDER by nama ASC")
    fun getAllQuran() : LiveData<List<QuranEntity>>

    @Query("SELECT* from tafsirentity ORDER by nama ASC")
    fun getAllTafsir() : LiveData<List<TafsirEntity>>


}