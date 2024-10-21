package com.findrepo.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.findrepo.repogallery.model.item.Repository

@Database(entities = [Repository::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao() : RepoDao
}