package com.sample.architecturecomponents.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.architecturecomponents.vo.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class ArchDB : RoomDatabase() {

    abstract fun userDao(): UserDao
}