package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class ToDo (
    @PrimaryKey(autoGenerate = true) val Id: Int =0,
    var Title: String = "",
    var Description: String = ""
)