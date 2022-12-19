package com.ma.development.a5oddam_archieve_app.domain.usecases

import androidx.lifecycle.LiveData
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import com.ma.development.a5oddam_archieve_app.domain.repository.MyRepository
import java.util.*
import javax.inject.Inject

class GetBirthDayKhoddamUseCase @Inject constructor(
    private val myRepository: MyRepository,
    private val getCurrentDateUseCase: GetCurrentDateUseCase
) {
    fun execute(): LiveData<List<Khadem>> {
        val currentDateString = getCurrentDateUseCase.execute()
        return myRepository.getBirthDayKoddam(currentDateString)
    }
}