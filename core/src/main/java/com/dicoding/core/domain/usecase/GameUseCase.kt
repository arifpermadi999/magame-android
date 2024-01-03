package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getAllGameBySearch(search: String): Flow<Resource<List<Game>>>
    fun getGameById(id:String): Flow<ApiResponse<DetailGameResponse>>
    suspend fun addFavorite(game: Game)
    suspend fun deleteFavorite(favorite: Favorite)
    fun getFavoriteByGame(game: Game): Flow<Favorite>
    fun getAllFavorite(): Flow<List<Favorite>>
}