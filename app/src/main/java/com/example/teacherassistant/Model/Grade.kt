package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(foreignKeys = [
    ForeignKey(
        entity = Student::class,
        parentColumns = ["Id"],
        childColumns = ["StudentId"],
        onDelete = CASCADE
    )
])

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