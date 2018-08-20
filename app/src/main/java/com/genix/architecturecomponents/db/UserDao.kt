package com.genix.architecturecomponents.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genix.architecturecomponents.vo.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Query("Select * From user Where id = :id")
    fun findById(id: String): LiveData<User>

    @Query("Select * From user where userName = :userName")
    fun findByUserName(userName: String): LiveData<User>

    @Query("Select * From user where userName like :userName and password like :password")
    fun login(userName: String, password: String): LiveData<User>
}