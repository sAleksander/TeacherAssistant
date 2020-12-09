package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.StudentCourseRelation

@Dao
interface StudentCourseRelationDAO {
    @Query("SELECT * FROM StudentCourseRelation WHERE StudentCourseRelation.CourseId = :courseId")
    fun getStudentsByCourse(courseId: Int): LiveData<StudentCourseRelation>

    @Query("SELECT * FROM studentcourserelation WHERE StudentCourseRelation.StudentId = :studentId")
    fun getCoursesByStudent(studentId: Int): LiveData<StudentCourseRelation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudentCourseRelation(studentCourseRelation: StudentCourseRelation)

    @Delete
    fun deleteStudentCourseRelation(studentCourseRelation: StudentCourseRelation)
}