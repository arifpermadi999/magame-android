package com.dicoding.core.data.source.remote.source
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.network.ApiService
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.data.source.remote.response.GameResponse
import com.dicoding.core.data.source.remote.response.GameScreenshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class GameRemoteSource(private val apiService: ApiService) {
    fun getAllGame(): Flow<ApiResponse<List<GameResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getAllGameBySearch(search:String): Flow<ApiResponse<List<GameResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListBySearch(search)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getGameDetailById(id:String): Flow<ApiResponse<DetailGameResponse>> {
        //get data from remote api
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getGameById(id)
                if (response == null){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getAllGameScreenshotsById(id:String): Flow<ApiResponse<GameScreenshots>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getScreenshotsGameById(id)

                if (response == null){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}