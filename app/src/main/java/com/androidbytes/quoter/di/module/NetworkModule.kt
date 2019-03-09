package com.androidbytes.quoter.di.module

import com.androidbytes.quoter.BuildConfig
import com.androidbytes.quoter.common.utils.BASE_URL
import com.androidbytes.quoter.repository.network.ApiClientService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by umang on 04/03/19.
 */
@Module
class NetworkModule {

    @Provides
    fun getRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }
            .build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getApiClientService(retrofit: Retrofit): ApiClientService {
        return retrofit.create<ApiClientService>(ApiClientService::class.java)
    }
}