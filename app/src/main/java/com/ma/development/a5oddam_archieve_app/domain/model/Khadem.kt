package com.ma.development.a5oddam_archieve_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "khoddam_table")
data class Khadem(
    val name: String? = null,
    val phone: Int? = null,
    var birthDate: String?= null,
    val phone2 : Int? = null,
    @PrimaryKey var key: Int? = null,
    val resamaDate : String? = null
)