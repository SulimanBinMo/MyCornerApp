package com.example.mycornerapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //    https://www.freetogame.com/api/games?platform=pc
    @GET("api/games")
    fun getAllGames(@Query("category")category:String): Call<Game>

}