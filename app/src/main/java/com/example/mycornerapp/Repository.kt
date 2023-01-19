package com.example.mycornerapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (var gameDao: GameDao, var apiInterface: ApiInterface) {

    fun getGameDB(): LiveData<List<GameTable>> {
        return gameDao.getGame()
    }
    fun getUserDB(): LiveData<List<Users>> {
        return gameDao.getUsers()
    }
    suspend fun adduser(user: Users) {
        gameDao.addUser(user)
    }


    suspend fun addGame(game: GameTable) {
        gameDao.addGame(game)
    }

    suspend fun deleteGame(game: GameTable) {
        gameDao.deleteGame(game)
    }

    val mutableLiveGame = MutableLiveData<List<GameItem>>()
    fun getGame(search: String): LiveData<List<GameItem>> {
        var gameList = ArrayList<GameItem>()
        apiInterface.getAllGames(search).enqueue(object : Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                Log.d("TAG", "Before the Null Check")
                val games = response.body()
                if (games != null) {
                    Log.d("TAG", "Before the loop")
                    for (game in games) {
                        Log.d("TAG", "Inside the loop")
                        gameList.add(game)
                        Log.d("SOULBOUND", "$game")
                        mutableLiveGame.postValue(gameList)
                    }
                }
                Log.d("TAG", "onResponse: AAA ")
            }
            override fun onFailure(call: Call<Game>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
        return mutableLiveGame
    }
}