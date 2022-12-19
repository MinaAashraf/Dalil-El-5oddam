package com.ma.development.a5oddam_archieve_app.data.local

import androidx.lifecycle.LiveData
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: KhademDao) : LocalDataSource {
    override fun getAllKhoddam(): LiveData<List<Khadem>> = dao.getAllKhoddam()

    override suspend fun insertKhadem(khadem: Khadem) {
        dao.insertKhadem(khadem)
    }

    override fun searchKhadem(search: String): LiveData<List<Khadem>> =
        dao.searchKhadem("%$search%")

    override fun getBirthDayKhoddam(date: String): LiveData<List<Khadem>> =
        dao.getBirthDayKhoddam(date)

    override suspend fun removeKhadem(khadem: Khadem) {
        dao.removeKhadem(khadem = khadem)
    }
}