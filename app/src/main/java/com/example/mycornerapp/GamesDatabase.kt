package com.example.mycornerapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameTable::class,Users::class], version = 3, exportSchema = false)
abstract class GamesDatabase : RoomDatabase()  {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var Instances: GamesDatabase? = null

        fun getDatabase(context: Context): GamesDatabase {
            val item = Instances
            if (item != null) {
                return item
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesDatabase::class.java,
                    "GameDB"
                ).fallbackToDestructiveMigration()
                    .build()
                Instances = instance
                return instance
            }
        }
    }
}