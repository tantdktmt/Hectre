package com.hectre.timesheet.di

import com.hectre.timesheet.domain.usecase.GetListJobUseCaseImpl
import com.hectre.timesheet.presentation.usecase.GetListJobUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TimesheetUseCaseModule {

    @Binds
    abstract fun provideGetListJobUseCase(
        getListJobUseCaseImpl: GetListJobUseCaseImpl
    ): GetListJobUseCase
}
