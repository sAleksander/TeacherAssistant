package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.Model.Grade

@Dao
interface GradeDAO {
    @Query("SELECT * FROM Grade, Student, Course WHERE Grade.StudentId = :studentId AND Grade.CourseId = :courseId ORDER BY grade ASC")
    fun getOrderedGrades(studentId: Int, courseId: Int): LiveData<Grade>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(grade:Grade)
}