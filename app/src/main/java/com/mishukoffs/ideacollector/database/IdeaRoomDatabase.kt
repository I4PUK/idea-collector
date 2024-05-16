package com.mishukoffs.ideacollector.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mishukoffs.ideacollector.database.dao.IdeaDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(IdeaEntity::class), version = 1, exportSchema = false)
public abstract class IdeaRoomDatabase : RoomDatabase() {

    abstract fun ideaDao(): IdeaDao

    companion object {
        @Volatile
        private var INSTANCE: IdeaRoomDatabase? = null

        fun getDatabase(context: Context): IdeaRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IdeaRoomDatabase::class.java,
                    "idea_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    class IdeaDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.ideaDao())
                }
            }
        }

        private suspend fun populateDatabase(ideaDao: IdeaDao) {
            // Delete all content here.
            ideaDao.deleteAll()

//        // Add sample words.
//        var word = IdeaEntity("Hello")
//        wordDao.insert(word)
//        word = Word("World!")
//        wordDao.insert(word)
//
//        // TODO: Add your own words!
        }
    }
}