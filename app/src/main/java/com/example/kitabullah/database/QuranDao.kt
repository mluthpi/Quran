package com.example.kitabullah.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kitabullah.model.QuranEntity


@Dao
interface QuranDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (quran: QuranEntity)

    @Delete
    fun delete (quran: QuranEntity)

    @Query("SELECT* from quranentity ORDER by nama ASC")
    fun getAllQuran() : LiveData<List<QuranEntity>>

}