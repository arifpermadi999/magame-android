package com.dicoding.core.data.source.repository

import android.content.Context
import com.dicoding.core.data.collector.DataBoundCollector
import com.dicoding.core.data.collector.ResultBound
import com.dicoding.core.data.source.local.source.GameLocalSource
import com.dicoding.core.data.source.mapper.GameMapper
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.DetailGameResponse
import com.dicoding.core.data.source.remote.response.GameResponse
import com.dicoding.core.data.source.remote.response.GameScreenshots
import com.dicoding.core.data.source.remote.source.GameRemoteSource
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import com.dicoding.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val context: Context,
    private val gameLocalSource: GameLocalSource,
    private val gameRemoteSource: GameRemoteSource
) : IGameRepository {
    override fun getAllGame(): Flow<ResultBound<List<Game>>> =
        object : DataBoundCollector<List<Game>, List<GameResponse>>(context) {
            override fun loadFromDB(): Flow<List<Game>> {
                return gameLocalSource.getAllData().map {
                    GameMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = gameRemoteSource.getAllGame()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = GameMapper.mapResponsesToEntities(data)
                gameLocalSource.deleteData()
                gameLocalSource.insertData(gameList)
            }

        }.execute()

    override fun getAllGameBySearch(search: String): Flow<ResultBound<List<Game>>>  =
        object : DataBoundCollector<List<Game>, List<GameResponse>>(context) {
            override fun loadFromDB(): Flow<List<Game>> {
                return gameLocalSource.getAllData().map {
                    GameMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                gameRemoteSource.getAllGameBySearch(search)

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = GameMapper.mapResponsesToEntities(data)
                gameLocalSource.deleteData()
                gameLocalSource.insertData(gameList)
            }

        }.execute()


    override fun getGameById(id: String): Flow<ApiResponse<DetailGameResponse>> =
        gameRemoteSource.getGameDetailById(id)

    override fun getGameScreenshotsById(id: String): Flow<ApiResponse<GameScreenshots>>  = gameRemoteSource.getAllGameScreenshotsById(id)

    override fun getFavoriteByGame(game: Game): Flow<Favorite> = flow {
        emitAll(gameLocalSource.getFavoriteByGameId(game.id?: "").map {
            if(it == null){
                Favorite()
            }else{
                GameMapper.favEntityToFavDomain(it)
            }
        })
    }


    override fun getAllFavorite(): Flow<List<Favorite>> = flow {
        emitAll(gameLocalSource.getAllDataFavorite().map { listFavoriteEntity ->
            listFavoriteEntity.map {
                GameMapper.favEntityToFavDomain(it)
            }
        })
    }

    override suspend fun insertFavorite(game: Game) = gameLocalSource.insertFavorite(
        GameMapper.gameDomainToFavEntity(game)
    )

    override suspend fun deleteFavorite(favorite: Favorite) = gameLocalSource.deleteFavorite(
        GameMapper.favDomainToFavEntity(favorite)
    )


}
