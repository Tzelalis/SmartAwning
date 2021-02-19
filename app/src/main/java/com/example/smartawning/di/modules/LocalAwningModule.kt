package com.example.smartawning.di.modules

import androidx.room.Update
import com.example.smartawning.data.localawning.LocalAwningDataSourceImpl
import com.example.smartawning.data.localawning.LocalAwningRepository
import com.example.smartawning.domain.datasource.LocalAwningDataSource
import com.example.smartawning.domain.entity.AppDatabase
import com.example.smartawning.framework.localawning.LocalAwningRepositoryImpl
import com.example.smartawning.usecase.localawning.DeleteLocalAwningUseCase
import com.example.smartawning.usecase.localawning.GetAllLocalAwningsUseCase
import com.example.smartawning.usecase.localawning.InsertLocalAwningUseCase
import com.example.smartawning.usecase.localawning.UpdateLocalAwningUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LocalAwningModule {

    @ViewModelScoped
    @Provides
    fun provideLocalAwningRepository(database: AppDatabase): LocalAwningRepository {
        return LocalAwningRepositoryImpl(database)
    }

    @ViewModelScoped
    @Provides
    fun provideLocalAwningDataSource(awningRepository: LocalAwningRepository): LocalAwningDataSource {
        return LocalAwningDataSourceImpl(awningRepository)
    }

    @ViewModelScoped
    @Provides
    fun provideLocalAwningUseCase(dataSource: LocalAwningDataSource): GetAllLocalAwningsUseCase {
        return GetAllLocalAwningsUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideDeleteLocalAwningUseCase(dataSource: LocalAwningDataSource): DeleteLocalAwningUseCase {
        return DeleteLocalAwningUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideInsertLocalAwningUseCase(dataSource: LocalAwningDataSource): InsertLocalAwningUseCase {
        return InsertLocalAwningUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateLocalAwningUseCase(dataSource: LocalAwningDataSource): UpdateLocalAwningUseCase {
        return UpdateLocalAwningUseCase(dataSource)
    }
}