package com.example.mycornerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var firstname:String,
    var lastname:String,
    val username:String,
    var email:String,
    var intreset:String,
    var pass:String
)
