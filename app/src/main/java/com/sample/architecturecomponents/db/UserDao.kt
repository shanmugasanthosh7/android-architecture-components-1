package com.sample.architecturecomponents.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sample.architecturecomponents.vo.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User): Long

    @Query("SELECT * FROM User WHERE id = :id")
    fun findById(id: String): User?

    @Query("SELECT * FROM User WHERE userName = :userName")
    fun findByUserName(userName: String): User?

    @Query("SELECT name FROM User WHERE id = :id")
    fun findName(id: String): LiveData<String>

    @Query("SELECT password FROM User WHERE id = :id")
    fun findPwd(id: String): LiveData<String>

    @Query("SELECT id FROM User WHERE userName = :userName AND password = :password")
    fun login(userName: String, password: String): String?

    @Query("SELECT COUNT(*) FROM User WHERE userName = :userName")
    fun userNameExists(userName: String): Int

    @Query("UPDATE User SET name = :name WHERE id = :id")
    fun updateUserName(name: String, id: String): Long

    @Query("UPDATE User SET password = :pwd WHERE id = :id")
    fun updatePassword(pwd: String, id: String): Long
}