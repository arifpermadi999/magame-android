package com.dicoding.core.data.source.local.entity.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: List<GameEntity>)

    @Query("delete from game")
    suspend fun deleteAlldata()

    @Query("SELECT * from game ORDER BY id ASC")
    fun getAllData(): Flow<List<GameEntity>>

}