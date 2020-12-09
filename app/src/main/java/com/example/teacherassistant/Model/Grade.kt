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
    val description: String = ""
)
{
   var updateDate: LocalDate? = LocalDate.now()
    fun getGrade():Int{
       return grade
    }
    fun setGrade(newGrade: Int){
        updateDate = LocalDate.now()
        grade = newGrade
    }
}