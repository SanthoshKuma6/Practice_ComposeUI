package com.sk.precticecomposeui.roomdb.mvvm

import com.sk.precticecomposeui.roomdb.AppDatabase
import com.sk.precticecomposeui.roomdb.LoginUser

class LoginRepo(val appDatabase: AppDatabase) {

    suspend fun loginInsert(login: LoginUser) = appDatabase.userDao().loginInsert(login)
    suspend fun getUser(userName: String, password: String): LoginUser? {
        return appDatabase.userDao().getUser(userName, password)
    }



    suspend fun getAllUsers(): List<LoginUser> {
        return appDatabase.userDao().getAllUsers()
    }


}