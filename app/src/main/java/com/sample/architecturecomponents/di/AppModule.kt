package com.sample.architecturecomponents.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.sample.architecturecomponents.db.ArchDB
import com.sample.architecturecomponents.db.UserDao
import com.sample.architecturecomponents.rx.AppSchedulerProvider
import com.sample.architecturecomponents.rx.SchedulerProvider
import com.sample.architecturecomponents.util.AppExecutors
import com.sample.architecturecomponents.vo.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDb(app: Application): ArchDB = Room
            .databaseBuilder(app, ArchDB::class.java, "arch.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(db: ArchDB): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun providePrefs(app: Application): SharedPreferences =
            app.applicationContext.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePrefsEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
            sharedPreferences.edit()

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    fun provideAppExecutors(): AppExecutors = AppExecutors()

}