package com.example.mycornerapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM GAMES ORDER BY ID ASC")
    fun getGame(): LiveData<List<GameTable>>

    @Insert
    suspend fun addGame(game: GameTable)

    @Delete
    suspend fun deleteGame(game: GameTable)

    @Update
    suspend fun updateGame(game: GameTable)

    @Query("SELECT * FROM Users ORDER BY ID ASC")
    fun getUsers(): LiveData<List<Users>>

    @Insert
    suspend fun addUser(user: Users)

}