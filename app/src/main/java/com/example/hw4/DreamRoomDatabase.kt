package com.example.hw4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Dream::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): DreamDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB = "dream_table"

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB)
                            .build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
}