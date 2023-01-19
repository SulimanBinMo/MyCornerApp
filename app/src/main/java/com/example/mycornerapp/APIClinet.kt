package com.example.mycornerapp

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClinet {
    var gsonBuilder = GsonBuilder().setLenient().create()
    var retrofit: Retrofit? = null
    fun getClint(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/")
            .addConverterFactory(
                GsonConverterFactory
                .create(gsonBuilder)).build()
        return retrofit
    }
}