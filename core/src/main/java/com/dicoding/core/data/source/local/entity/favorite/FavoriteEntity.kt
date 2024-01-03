package com.dicoding.core.data.source.local.entity.favorite



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name= "game_id")
    var gameId: String,

    @ColumnInfo(name= "name")
    var name: String,

    @ColumnInfo(name= "rating")
    var rating: Float,

    @ColumnInfo(name= "ratings_count")
    var ratingsCount: Int,

    @ColumnInfo(name= "platform")
    var platform: String,

    @ColumnInfo(name= "image")
    var image: String,
)