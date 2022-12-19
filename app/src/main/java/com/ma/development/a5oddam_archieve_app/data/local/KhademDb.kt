package com.ma.development.a5oddam_archieve_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem

@Database(entities = [Khadem::class], version = 1, exportSchema = false)
abstract class KhademDb : RoomDatabase() {
    abstract fun getDao(): KhademDao
}