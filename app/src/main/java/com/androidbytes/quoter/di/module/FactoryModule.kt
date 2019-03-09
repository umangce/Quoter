package com.androidbytes.quoter.di.module

import com.androidbytes.quoter.QuoteApp
import com.androidbytes.quoter.common.factory.ViewModelFactory
import com.androidbytes.quoter.repository.QuoteRepository
import dagger.Module
import dagger.Provides

/**
 * Created by umang on 04/03/19.
 */
@Module
class FactoryModule {

    @Provides
    fun provideViewModelFactory(application: QuoteApp, quoteRepository: QuoteRepository) =
        ViewModelFactory(application, quoteRepository)
}