package com.example.smartawning.di.modules

import com.example.smartawning.BuildConfig
import com.example.smartawning.data.awning.AwningDataSourceImpl
import com.example.smartawning.data.awning.AwningRepository
import com.example.smartawning.di.scopes.AwningHttpClient
import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.framework.awning.AwningApi
import com.example.smartawning.framework.awning.AwningRepositoryImpl
import com.example.smartawning.usecase.awning.*
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object AwningModule {

    @ViewModelScoped
    @Provides
    fun provideAwningRepository(awningApi: AwningApi): AwningRepository {
        return AwningRepositoryImpl(awningApi)
    }

    @ViewModelScoped
    @Provides
    fun provideAwningDataSource(awningRepository: AwningRepository): AwningDataSource {
        return AwningDataSourceImpl(awningRepository)
    }

    @ViewModelScoped
    @Provides
    fun provideRetrofitForAwning(@AwningHttpClient okHttpClient: OkHttpClient, moshi: Moshi): AwningApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://46.176.166.222:300/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(AwningApi::class.java)
    }

    @AwningHttpClient
    @ViewModelScoped
    @Provides
    fun provideAwningClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    //.addHeader("Username", "pao")
                    .build()
                it.proceed(request)
            }
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)
        }

        return okHttpClient.build()
    }

    @ViewModelScoped
    @Provides
    fun provideAwningConfigUseCase(dataSource: AwningDataSource): AwningConfigUseCase {
        return AwningConfigUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateAwningPositionUseCase(dataSource: AwningDataSource): UpdateAwningPositionUseCase {
        return UpdateAwningPositionUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateRainSensorUseCase(dataSource: AwningDataSource): UpdateRainSensorUseCase {
        return UpdateRainSensorUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateSunSensorUseCase(dataSource: AwningDataSource): UpdateSunSensorUseCase {
        return UpdateSunSensorUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateEnableProgramUseCase(dataSource: AwningDataSource): UpdateEnableProgramUseCase {
        return UpdateEnableProgramUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateStartTimeProgramUseCase(dataSource: AwningDataSource): UpdateStartTimeProgramUseCase {
        return UpdateStartTimeProgramUseCase(dataSource)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateEndTimeProgramUseCase(dataSource: AwningDataSource): UpdateEndTimeProgramUseCase {
        return UpdateEndTimeProgramUseCase(dataSource)
    }
}