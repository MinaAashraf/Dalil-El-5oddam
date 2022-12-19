package com.ma.development.a5oddam_archieve_app.domain.repository

import androidx.lifecycle.LiveData
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem

interface MyRepository {

   fun getAllKoddam () : LiveData<List<Khadem>>
   fun getBirthDayKoddam (date:String) : LiveData<List<Khadem>>
   fun searchKhadem (search:String) : LiveData<List<Khadem>>
   suspend fun refreshData ()





}