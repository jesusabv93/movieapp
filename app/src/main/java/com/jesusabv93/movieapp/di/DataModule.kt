package com.jesusabv93.movieapp.di

import com.jesusabv93.data.datasources.AuthRemoteDataSource
import com.jesusabv93.movieapp.data.mock.AuthMockDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAuthRemoteDataSource(): AuthRemoteDataSource = AuthMockDataSource()

}