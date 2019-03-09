package com.androidbytes.quoter.di.module

import com.androidbytes.quoter.view.QuoteActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by umang on 04/03/19.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeQuoteActivity(): QuoteActivity
}