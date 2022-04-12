package com.hectre.timesheet.di

import com.hectre.timesheet.data.TimesheetRepositoryImpl
import com.hectre.timesheet.domain.repository.TimesheetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TimesheetRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(timesheetRepositoryImpl: TimesheetRepositoryImpl): TimesheetRepository
}
