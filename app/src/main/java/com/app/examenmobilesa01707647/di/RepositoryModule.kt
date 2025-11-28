package com.app.examenmobilesa01707647.di

import com.app.examenmobilesa01707647.data.repository.CovidRepositoryImpl
import com.app.examenmobilesa01707647.domain.repository.CovidRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCovidRepository(impl: CovidRepositoryImpl): CovidRepository
}
