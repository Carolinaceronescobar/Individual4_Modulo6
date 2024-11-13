package com.example.individual1_modulo6.model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.individual1_modulo6.model.Remote.MarsRealState

@Database(entities =[ MarsRealState::class] , version = 1)
abstract class MarsDatabase : RoomDatabase() {

    // REFERENCIA AL DAO PARTE LOCAL
    abstract fun marsDao(): MarsDao

    companion object {
        @Volatile
        private var INSTANCE: MarsDatabase? = null

        fun getDataBase(context: Context): MarsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarsDatabase::class.java,
                    "terrain_marts"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}