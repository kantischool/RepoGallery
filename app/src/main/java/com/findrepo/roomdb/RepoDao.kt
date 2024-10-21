package com.findrepo.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.findrepo.model.Repository

@Dao
interface RepoDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepositories(repo: List<Repository>)

    @Query("SELECT * FROM Repositories")
    suspend fun getRepositories() : List<Repository>
}