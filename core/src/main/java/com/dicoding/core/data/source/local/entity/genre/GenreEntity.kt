package com.dicoding.core.data.source.local.entity.genre


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name= "id_genre")
    var idGenre: String,


    @ColumnInfo(name= "name")
    var name: String


)