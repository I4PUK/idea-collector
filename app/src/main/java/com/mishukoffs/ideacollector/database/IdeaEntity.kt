package com.mishukoffs.ideacollector.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mishukoffs.ideacollector.model.IdeaPriority

@Entity(tableName = "ideas")
data class IdeaEntity (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "created_date") var createdDate: String,
    @ColumnInfo(name = "priority") val priority: IdeaPriority,
)