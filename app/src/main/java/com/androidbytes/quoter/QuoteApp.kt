package com.androidbytes.quoter

import com.androidbytes.quoter.di.component.DaggerAppComponent
import com.androidbytes.quoter.di.component.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by umang on 04/03/19.
 */
class QuoteApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .coreComponent(DaggerCoreComponent.builder().build())
            .build()
        appComponent.inject(this)

        return appComponent
    }
}