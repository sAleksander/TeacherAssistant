package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Grade

@Dao
interface GradeDAO {
    @Query("SELECT * FROM Grade, Student, Course WHERE Grade.StudentId = :studentId AND Grade.CourseId = :courseId ORDER BY grade ASC")
    fun getOrderedGrades(studentId: Int, courseId: Int): LiveData<Grade>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(grade:Grade)

    @Update
    fun updateGrade(grade: Grade)

    @Delete
    fun deleteGrade(grade: Grade)
}