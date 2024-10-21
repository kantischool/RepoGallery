package com.findrepo.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.findrepo.model.Repository

@Database(entities = [Repository::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao() : RepoDao

    companion object {
        @Volatile
        private var DATABASE_INSTANCE : RepoDatabase? = null
        fun getDatabase(context: Context) : RepoDatabase{
            if(DATABASE_INSTANCE == null){
                synchronized(this){
                    DATABASE_INSTANCE = Room.databaseBuilder(context.applicationContext,RepoDatabase::class.java, "RepoDatabase").build()
                }
            }
            return DATABASE_INSTANCE!!
        }
    }
}