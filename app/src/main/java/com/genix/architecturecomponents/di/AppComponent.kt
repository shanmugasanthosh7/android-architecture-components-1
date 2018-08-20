package com.genix.architecturecomponents.di

import android.app.Application
import com.genix.architecturecomponents.ArchApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(archApp: ArchApp)

    override fun inject(instance: DaggerApplication?)
}