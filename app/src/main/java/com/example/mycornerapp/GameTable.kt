package com.example.mycornerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Games")
data class GameTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val platform: String,
    val genre: String,
    val short_description: String,
    val game_url: String,
    val image_url:String,
    val userid:Int
)
