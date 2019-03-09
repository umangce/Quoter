package com.androidbytes.quoter.di.component

import com.androidbytes.quoter.QuoteApp
import com.androidbytes.quoter.di.module.ActivityModule
import com.androidbytes.quoter.di.module.FactoryModule
import com.androidbytes.quoter.di.scope.ActivityScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by umang on 04/03/19.
 */
@ActivityScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AndroidSupportInjectionModule::class, FactoryModule::class, ActivityModule::class]
)
interface AppComponent : AndroidInjector<QuoteApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: QuoteApp): Builder

        fun coreComponent(component: CoreComponent): Builder

        fun build(): AppComponent
    }
}