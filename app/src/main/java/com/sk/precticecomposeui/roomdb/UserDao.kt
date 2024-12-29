package com.sk.precticecomposeui.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun loginInsert(login:LoginUser)

    @Query("SELECT * FROM login_user WHERE userName = :username AND password = :password")
    suspend fun getUser(username: String, password: String): LoginUser?

    @Query("SELECT * FROM login_user")
    suspend fun getAllUsers(): List<LoginUser>

}