package com.ma.development.a5oddam_archieve_app.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem


@Dao
interface KhademDao {

    @Query("Select * from khoddam_table order by name")
    fun getAllKhoddam(): LiveData<List<Khadem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKhadem(khadem: Khadem)


    @Query("Select * from khoddam_table where name like :search")
    fun searchKhadem(search: String): LiveData<List<Khadem>>

    @Query("Select * from khoddam_table where birthDate = :date order by name")
    fun getBirthDayKhoddam(date: String): LiveData<List<Khadem>>

    @Delete
    suspend fun removeKhadem(khadem: Khadem)



}