package com.dicoding.core.data.collector

import android.content.Context
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.utils.Connectivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class DataBoundCollector<ResultType,RequestType>(context:Context) {
    private var context: Context
    init {
        this.context = context
    }


        fun execute() = flow<ResultBound<ResultType>>{
        emit(ResultBound.Loading());val db = loadFromDB()
        if(Connectivity.isOnline(context)){
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Empty -> emitAll(db.map { ResultBound.Success(it) })
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(db.map { ResultBound.Success(it) })
                }is ApiResponse.Error -> {
                emit(ResultBound.Error(apiResponse.errorMessage))
            }else -> emitAll(db.map { ResultBound.Success(it) })}
        }else{
            emitAll(db.map { ResultBound.Success(it) })
        }
    }
    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)



}