package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.data.source.remote.response.GameScreenshots
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import com.dicoding.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGame(): Flow<Resource<List<Game>>> = gameRepository.getAllGame()
    override fun getAllGameBySearch(search: String): Flow<Resource<List<Game>>> = gameRepository.getAllGameBySearch(search)

    override fun getGameById(id: String): Flow<ApiResponse<DetailGameResponse>> = gameRepository.getGameById(id)
    override fun getGameScreenshotsById(id: String): Flow<ApiResponse<GameScreenshots>> = gameRepository.getGameScreenshotsById(id)

    override fun getFavoriteByGame(game: Game) = gameRepository.getFavoriteByGame(game)
    override fun getAllFavorite(): Flow<List<Favorite>> = gameRepository.getAllFavorite()
    override suspend  fun addFavorite(game: Game) =  gameRepository.insertFavorite(game)
    override suspend fun deleteFavorite(favorite: Favorite) = gameRepository.deleteFavorite(favorite)
}