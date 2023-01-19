package com.example.myproject.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myproject.Database.DAO.ProblemsDao
import com.example.myproject.Database.Entity.ProblemsEntity


@Database(
    version = 1,
    entities = [ProblemsEntity::class],
)

abstract class AppDatabase :RoomDatabase(){

    abstract fun articleDao(): ProblemsDao
}