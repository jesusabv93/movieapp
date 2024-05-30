package com.jesusabv93.movieapp.di

import com.jesusabv93.data.datasources.MoviesLocalDataSource
import com.jesusabv93.data.datasources.MoviesRemoteDataSource
import com.jesusabv93.movieapp.data.api.MoviesServerDataSource
import com.jesusabv93.movieapp.data.database.MoviesRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: MoviesRoomDataSource): MoviesLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MoviesServerDataSource): MoviesRemoteDataSource

}