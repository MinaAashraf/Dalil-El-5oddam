package com.ma.development.a5oddam_archieve_app.domain.usecases

import androidx.lifecycle.LiveData
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import com.ma.development.a5oddam_archieve_app.domain.repository.MyRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val myRepository: MyRepository) {
    fun execute(search: String): LiveData<List<Khadem>> = myRepository.searchKhadem(search)
}