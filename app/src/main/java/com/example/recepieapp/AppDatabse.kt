package com.example.recepieapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDatabse: RoomDatabase() {
    abstract fun getDao():Dao
}
