package com.dicoding.core.data.source.local.entity.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoriteEntity)
    @Delete
    suspend fun delete(favorite: FavoriteEntity)
    @Query("SELECT * from favorite ORDER BY id ASC")
    fun getAllData(): Flow<List<FavoriteEntity>>

    @Query("SELECT * from favorite where game_id = :gameId  ORDER BY id ASC")
    fun getDataByGameId(gameId:String): Flow<FavoriteEntity>
}