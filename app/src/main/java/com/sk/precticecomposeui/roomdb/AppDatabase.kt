package com.sk.precticecomposeui.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LoginUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instent: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return instent ?: synchronized(this) {
                var instent = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "Data base"

                ).fallbackToDestructiveMigration()
                    .build()

                instent = instent
                instent
            }
        }
    }
}