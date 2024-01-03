package com.dicoding.core.data.source.local.source

import com.dicoding.core.data.source.local.entity.favorite.FavoriteDao
import com.dicoding.core.data.source.local.entity.favorite.FavoriteEntity
import com.dicoding.core.data.source.local.entity.game.GameDao
import com.dicoding.core.data.source.local.entity.game.GameEntity
import kotlinx.coroutines.flow.Flow

class GameLocalSource(private val gameDao: GameDao,private val favoriteDao: FavoriteDao) {
    fun getAllData() : Flow<List<GameEntity>> = gameDao.getAllData()
    suspend fun insertData(games: List<GameEntity>) = gameDao.insert(games)
    suspend fun deleteData() = gameDao.deleteAlldata()

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.insert(favoriteEntity)
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.delete(favoriteEntity)
    fun getAllDataFavorite() : Flow<List<FavoriteEntity>> = favoriteDao.getAllData()
    fun getFavoriteByGameId(gameId: String) : Flow<FavoriteEntity> = favoriteDao.getDataByGameId(gameId)

}