package com.mishukoffs.ideacollector.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishukoffs.ideacollector.database.IdeaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeaDao {
    @Query("SELECT * FROM ideas")
    fun getAlphabetizedIdeas(): Flow<List<IdeaEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(idea: IdeaEntity)

    @Query("DELETE FROM ideas")
    suspend fun deleteAll()
}