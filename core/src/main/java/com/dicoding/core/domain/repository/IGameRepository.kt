package com.dicoding.core.domain.repository

import com.dicoding.core.data.Resource
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.data.source.remote.response.GameScreenshots
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getAllGameBySearch(search: String): Flow<Resource<List<Game>>>
    fun getGameById(id:String): Flow<ApiResponse<DetailGameResponse>>
    fun getGameScreenshotsById(id:String): Flow<ApiResponse<GameScreenshots>>

    fun getFavoriteByGame(game: Game): Flow<Favorite>
    fun getAllFavorite(): Flow<List<Favorite>>
    suspend fun insertFavorite(game: Game)
    suspend fun deleteFavorite(favorite: Favorite)
}