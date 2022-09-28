package com.mehmetkaya.btcchallenge.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mehmetkaya.btcchallenge.di.qualifier.CommonApi
import com.mehmetkaya.btcchallenge.di.qualifier.GraphApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(20L, SECONDS)
            .readTimeout(60L, SECONDS)
            .writeTimeout(120L, SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @CommonApi
    fun provideCommonRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(COMMON_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @GraphApi
    fun provideGraphRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(GRAPH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    companion object {
        private const val COMMON_BASE_URL = "https://api.btcturk.com/api/"
        private const val GRAPH_BASE_URL = "https://graph-api.btcturk.com/"
    }
}
