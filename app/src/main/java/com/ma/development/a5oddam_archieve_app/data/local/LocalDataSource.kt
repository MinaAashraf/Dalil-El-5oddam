package com.ma.development.a5oddam_archieve_app.data.local

import androidx.lifecycle.LiveData
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem

interface LocalDataSource {

    fun getAllKhoddam () : LiveData<List<Khadem>>
    suspend fun insertKhadem (khadem: Khadem)
    fun searchKhadem (date:String) : LiveData<List<Khadem>>
    fun getBirthDayKhoddam (search:String) : LiveData<List<Khadem>>
    suspend fun removeKhadem (khadem: Khadem)

}