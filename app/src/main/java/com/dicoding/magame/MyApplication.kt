package com.dicoding.magame

import android.content.Context
import com.dicoding.core.di.databaseModule
import com.dicoding.core.di.networkModule
import com.dicoding.core.di.repositoryModule
import com.dicoding.magame.di.useCaseModule
import com.dicoding.magame.di.viewModelModule
import com.dicoding.magame.utils.ReleaseLogTree
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : SplitCompatApplication() {


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }else{
            Timber.plant(ReleaseLogTree())
        }
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

    companion object {
        private lateinit var appContext: Context
    }
}