package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class Student(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    var FirstName:String,
    var LastName:String
)
