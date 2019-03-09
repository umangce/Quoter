package com.androidbytes.quoter.di.module

import com.androidbytes.quoter.repository.QuoteRepository
import com.androidbytes.quoter.repository.network.ApiClientService
import dagger.Module
import dagger.Provides

/**
 * Created by umang on 04/03/19.
 */
@Module(includes = [NetworkModule::class])
class RepoModule {

    @Provides
    fun provideQuoteRepository(apiClientService: ApiClientService): QuoteRepository {
        return QuoteRepository(apiClientService)
    }
}