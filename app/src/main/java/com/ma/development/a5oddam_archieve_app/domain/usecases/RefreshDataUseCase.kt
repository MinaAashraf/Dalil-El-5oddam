package com.ma.development.a5oddam_archieve_app.domain.usecases

import com.ma.development.a5oddam_archieve_app.domain.repository.MyRepository
import javax.inject.Inject

class RefreshDataUseCase @Inject constructor(private val myRepository: MyRepository) {

    suspend fun execute() {
        myRepository.refreshData()
    }
}
