package com.dicoding.core.data.source.remote.network
import com.dicoding.core.BuildConfig
import com.dicoding.core.data.source.remote.response.BaseGameResponse
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.data.source.remote.response.GameScreenshots
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games?token&key=${BuildConfig.API_TOKEN}")
    suspend fun getList() : BaseGameResponse

    @GET("games?token&key=${BuildConfig.API_TOKEN}")
    suspend fun getListBySearch(@Query("search") search:String) : BaseGameResponse

    @GET("games/{id}?token&key=${BuildConfig.API_TOKEN}")
    suspend fun getGameById(@Path("id") id:String) : DetailGameResponse


    @GET("games/{id}/screenshots?token&key=${BuildConfig.API_TOKEN}")
    suspend fun getScreenshotsGameById(@Path("id") id:String) : GameScreenshots



}