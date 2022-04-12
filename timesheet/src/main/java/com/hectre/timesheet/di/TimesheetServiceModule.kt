package com.hectre.timesheet.di

import com.hectre.timesheet.data.TimesheetService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimesheetServiceModule {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) = retrofit.create(TimesheetService::class.java)
}
