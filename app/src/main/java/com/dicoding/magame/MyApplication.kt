package com.dicoding.magame

import com.dicoding.core.di.databaseModule
import com.dicoding.core.di.networkModule
import com.dicoding.core.di.repositoryModule
import com.dicoding.magame.di.useCaseModule
import com.dicoding.magame.di.viewModelModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule)
        }
    }
}