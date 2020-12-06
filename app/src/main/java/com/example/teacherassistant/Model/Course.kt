package com.example.teacherassistant.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Course(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val Name: String
)