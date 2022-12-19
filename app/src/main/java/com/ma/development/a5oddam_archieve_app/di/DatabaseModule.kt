package com.ma.development.a5oddam_archieve_app.di

import android.content.Context
import androidx.room.Room
import com.ma.development.a5oddam_archieve_app.R
import com.ma.development.a5oddam_archieve_app.data.local.KhademDao
import com.ma.development.a5oddam_archieve_app.data.local.KhademDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context): KhademDb =
        Room.databaseBuilder(
            context,
            KhademDb::class.java,
            context.getString(R.string.khadem_database_name)
        ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideKhademDao(khademDb: KhademDb): KhademDao = khademDb.getDao()


}