package com.ma.development.a5oddam_archieve_app.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.ma.development.a5oddam_archieve_app.data.local.LocalDataSource
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import com.ma.development.a5oddam_archieve_app.domain.repository.MyRepository
import com.ma.development.a5oddam_archieve_app.domain.usecases.GetFormattedDateString
import kotlinx.coroutines.*
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val firebaseReference: DatabaseReference,
    private val getFormattedDateString: GetFormattedDateString
) : MyRepository {
    override fun getAllKoddam() = localDataSource.getAllKhoddam()
    override fun getBirthDayKoddam(date: String): LiveData<List<Khadem>> =
        localDataSource.getBirthDayKhoddam(date)

    override fun searchKhadem(search: String): LiveData<List<Khadem>> =
        localDataSource.searchKhadem(search)

    override suspend fun refreshData() {
        firebaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val khadem = snapshot.getValue(Khadem::class.java)
                if (khadem?.name != null && khadem.key != null) {
                    khadem.birthDate = handleBirthDateFormat(khadem.birthDate)
                    CoroutineScope(Dispatchers.IO).launch {
                        localDataSource.insertKhadem(khadem)
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val khadem = snapshot.getValue(Khadem::class.java)
                if (khadem?.name != null && khadem.key != null) {
                    khadem.birthDate = handleBirthDateFormat(khadem.birthDate)
                    CoroutineScope(Dispatchers.IO).launch {
                        localDataSource.insertKhadem(khadem)
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                snapshot.getValue(Khadem::class.java)?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        localDataSource.removeKhadem(it)
                    }
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun handleBirthDateFormat(birthDate: String?): String? {
        return if (birthDate != null && Regex("([0-9]{2}|[1-9])/([0-9]{2}|[1-9])/([0-9]{4})").matches(
                birthDate!!
            )
        )
            getFormattedDateString.execute(birthDate ?: "")
        else if (birthDate != null && Regex("([0-9]{2}|[1-9])/([0-9]{2}|[1-9])").matches(birthDate!!))
            getFormattedDateString.execute(birthDate ?: "", false)
        else
            birthDate
    }

}