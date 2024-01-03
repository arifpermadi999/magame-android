package com.dicoding.core.data.source.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.source.local.entity.favorite.FavoriteDao
import com.dicoding.core.data.source.local.entity.favorite.FavoriteEntity
import com.dicoding.core.data.source.local.entity.game.GameDao
import com.dicoding.core.data.source.local.entity.game.GameEntity

@Database(entities = [GameEntity::class,FavoriteEntity::class], version = 3, exportSchema = false)
abstract class DatabaseGame : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun favoriteDao(): FavoriteDao
}