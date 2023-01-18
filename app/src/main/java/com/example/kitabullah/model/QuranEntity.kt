package com.example.kitabullah.model

import androidx.room.PrimaryKey
import androidx.room.Entity


@Entity
data class QuranEntity (
    @PrimaryKey(autoGenerate = true)
    var nomor : Int? = 0,

    var namaLatin : String? = null,

    var nama : String? = null,

    var arti : String? = null,



        )