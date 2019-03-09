package com.androidbytes.quoter.di.component

import com.androidbytes.quoter.di.module.RepoModule
import com.androidbytes.quoter.repository.QuoteRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by umang on 04/03/19.
 */

@Singleton
@Component(modules = [RepoModule::class])
interface CoreComponent {

    @Component.Builder
    interface Builder {

        fun build(): CoreComponent
    }

    fun provideQuoteRepository(): QuoteRepository
}