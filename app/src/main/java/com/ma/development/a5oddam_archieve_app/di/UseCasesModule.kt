package com.ma.development.a5oddam_archieve_app.di

import com.ma.development.a5oddam_archieve_app.domain.usecases.GetCurrentDateUseCase
import com.ma.development.a5oddam_archieve_app.domain.usecases.GetFormattedDateString
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {


    @Singleton
    @Provides
    fun provideGetCurrentDateUseCase() = GetCurrentDateUseCase()

    @Singleton
    @Provides
    fun provideGetFormattedDateUseCase() = GetFormattedDateString()


}