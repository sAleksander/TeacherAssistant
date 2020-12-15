package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity()
class Grade(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val StudentId: Int,
    val CourseId: Int,
    private var grade: Int,
    var description: String = "",
    var updateDate: String = LocalDate.now().toString()
)
{
    fun getGrade():Int{
       return grade
    }
    fun setGrade(newGrade: Int){
        updateDate = LocalDate.now().toString()
        grade = newGrade
    }
}