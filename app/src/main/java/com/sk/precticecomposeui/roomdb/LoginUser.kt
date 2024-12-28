package com.sk.precticecomposeui.roomdb

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "login_user")
data class LoginUser(
    val userName:String?=null,
    val password:String?=null,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0
):Parcelable
