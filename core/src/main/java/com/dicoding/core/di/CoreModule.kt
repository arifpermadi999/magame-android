package com.dicoding.core.di

import androidx.room.Room
import com.dicoding.core.BuildConfig
import com.dicoding.core.data.source.local.DatabaseGame
import com.dicoding.core.data.source.local.source.GameLocalSource
import com.dicoding.core.data.source.remote.network.ApiService
import com.dicoding.core.data.source.remote.source.GameRemoteSource
import com.dicoding.core.data.source.repository.GameRepository
import com.dicoding.core.domain.repository.IGameRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module{
    factory{
        get<DatabaseGame>().gameDao()
    }
    factory{
        get<DatabaseGame>().favoriteDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            DatabaseGame::class.java,
            "Game.db"
            ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module{
    single { GameLocalSource(get(),get()) }
    single { GameRemoteSource(get()) }
    single<IGameRepository> {
        GameRepository(get(),get())
    }
}