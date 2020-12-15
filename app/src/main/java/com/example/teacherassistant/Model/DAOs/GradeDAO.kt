package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Grade

@Dao
interface GradeDAO {
    @Query("SELECT * FROM Grade, Student, Course WHERE Grade.StudentId = :studentId AND Grade.CourseId = :courseId")
    fun getOrderedGrades(studentId: Int, courseId: Int): LiveData<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(grade:Grade)

    @Update
    suspend fun updateGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)
}