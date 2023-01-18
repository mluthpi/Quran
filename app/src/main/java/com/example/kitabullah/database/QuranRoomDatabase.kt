package com.example.kitabullah.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kitabullah.model.QuranEntity


@Database(entities = [QuranEntity::class], version = 3)
abstract class QuranRoomDatabase : RoomDatabase() {

    abstract fun quranDao() : QuranDao

    companion object {
        @Volatile
        private var INSTANCE: QuranRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context : Context): QuranRoomDatabase {
            if (INSTANCE == null) {
                synchronized(QuranRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    QuranRoomDatabase::class.java, "quran_database")
                        .build()
                }
            }
            return INSTANCE as QuranRoomDatabase
        }
    }
}