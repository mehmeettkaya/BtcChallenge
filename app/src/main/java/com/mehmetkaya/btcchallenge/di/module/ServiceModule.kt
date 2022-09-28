package com.mehmetkaya.btcchallenge.di.module

import com.mehmetkaya.btcchallenge.data.service.CommonService
import com.mehmetkaya.btcchallenge.data.service.GraphService
import com.mehmetkaya.btcchallenge.di.qualifier.CommonApi
import com.mehmetkaya.btcchallenge.di.qualifier.GraphApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideCommonService(
        @CommonApi retrofit: Retrofit
    ): CommonService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideGraphService(
        @GraphApi retrofit: Retrofit
    ): GraphService {
        return retrofit.create()
    }
}
