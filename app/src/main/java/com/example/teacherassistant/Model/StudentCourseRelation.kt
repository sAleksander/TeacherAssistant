package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class StudentCourseRelation(
   @PrimaryKey(autoGenerate = true) val Id: Int = 0,
   val StudentId: Int,
   val CourseId: Int
)