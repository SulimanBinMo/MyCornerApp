package com.example.mycornerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel (application: Application): AndroidViewModel(application) {
    lateinit var repository : Repository
    private var gameDB: GamesDatabase
    private var gameDao : GameDao

    init {
        gameDB = GamesDatabase.getDatabase(application)
        gameDao = gameDB.gameDao()

        var checkAPI = APIClinet().getClint()
        if (checkAPI != null) {
            var apiInterface = checkAPI.create(ApiInterface::class.java)
            repository = Repository(gameDao,apiInterface)
        }
    }

    fun addGame(game: GameTable){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addGame(game)
        }
    }
    fun adduser(user: Users){
        CoroutineScope(Dispatchers.IO).launch {
            repository.adduser(user)
        }
    }

    fun deleteGame(game: GameTable){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteGame(game)
        }
    }

    fun getGame(): LiveData<List<GameTable>> {
        return repository.getGameDB()
    }


    fun getGameAPI(search:String): LiveData<List<GameItem>> {
        return repository.getGame(search)
    }
    fun getUsers() :LiveData<List<Users>> {
        return repository.getUserDB()
    }
}