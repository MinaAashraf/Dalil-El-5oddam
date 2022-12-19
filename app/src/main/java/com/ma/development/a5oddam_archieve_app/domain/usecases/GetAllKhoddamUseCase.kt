package com.ma.development.a5oddam_archieve_app.domain.usecases

import androidx.lifecycle.LiveData
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import com.ma.development.a5oddam_archieve_app.domain.repository.MyRepository
import javax.inject.Inject

class GetAllKhoddamUseCase @Inject constructor(private val myRepository: MyRepository) {
    fun execute(): LiveData<List<Khadem>> = myRepository.getAllKoddam()
}