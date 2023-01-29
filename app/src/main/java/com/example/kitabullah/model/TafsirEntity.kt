package com.example.kitabullah.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TafsirEntity (
    @PrimaryKey(autoGenerate = true)
    var nomor : Int? = 0,

    var namaLatin : String? = null,

    var nama : String? = null,

    var arti : String? = null

    )