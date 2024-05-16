package com.mishukoffs.ideacollector

import android.app.Application
import com.mishukoffs.ideacollector.database.IdeaRoomDatabase
import com.mishukoffs.ideacollector.repository.IdeaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class IdeaListApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { IdeaRoomDatabase.getDatabase(this) }
    val repository by lazy { IdeaRepository(database.ideaDao()) }
}