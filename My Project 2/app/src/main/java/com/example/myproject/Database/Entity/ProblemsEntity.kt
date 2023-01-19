package com.example.myproject.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PROBLEMS")
data class ProblemsEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String?,
    val dose: String?,
    val strength: String?
)