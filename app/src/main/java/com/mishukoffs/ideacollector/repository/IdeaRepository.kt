package com.mishukoffs.ideacollector.repository

import androidx.annotation.WorkerThread
import androidx.room.Dao
import com.mishukoffs.ideacollector.database.IdeaEntity
import com.mishukoffs.ideacollector.database.dao.IdeaDao

@Dao
class IdeaRepository(private val ideaDao: IdeaDao){
    val allIdeas = ideaDao.getAlphabetizedIdeas()

    @WorkerThread
    suspend fun insert(word: IdeaEntity) {
        ideaDao.insert(word)
    }
}