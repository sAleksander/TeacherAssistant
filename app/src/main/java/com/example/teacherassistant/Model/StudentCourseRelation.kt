package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
   ForeignKey(
      entity = Student::class,
      parentColumns = ["Id"],
      childColumns = ["StudentId"],
      onDelete = ForeignKey.CASCADE
   ),
   ForeignKey(
      entity = Course::class,
      parentColumns = ["Id"],
      childColumns = ["CourseId"],
      onDelete = ForeignKey.CASCADE
   )
])

class StudentCourseRelation(
   @PrimaryKey(autoGenerate = true) val Id: Int = 0,
   val StudentId: Int,
   val CourseId: Int
)