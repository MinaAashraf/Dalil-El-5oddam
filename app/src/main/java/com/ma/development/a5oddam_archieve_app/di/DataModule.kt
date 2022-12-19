package com.ma.development.a5oddam_archieve_app.di

import com.ma.development.a5oddam_archieve_app.data.local.LocalDataSource
import com.ma.development.a5oddam_archieve_app.data.local.LocalDataSourceImpl
import com.ma.development.a5oddam_archieve_app.data.repository.MyRepositoryImpl
import com.ma.development.a5oddam_archieve_app.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Singleton
    @Binds
    abstract fun provideRepoDataSource(repo : MyRepositoryImpl): MyRepository

}