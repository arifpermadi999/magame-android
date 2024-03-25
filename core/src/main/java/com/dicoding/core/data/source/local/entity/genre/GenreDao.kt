package com.dicoding.core.data.source.local.entity.genre

import androidx.room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dao: GenreDao)
    @Update
    suspend fun update(dao: GenreDao)
    @Delete
    suspend fun delete(dao: GenreDao)

    @Query("SELECT * from genres ORDER BY id ASC")
    fun getAllData(): LiveData<List<GenreDao>>
}