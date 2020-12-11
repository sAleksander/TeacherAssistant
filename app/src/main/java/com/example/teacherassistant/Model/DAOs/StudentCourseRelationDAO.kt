package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.StudentCourseRelation

@Dao
interface StudentCourseRelationDAO {
    @Query("SELECT * FROM StudentCourseRelation WHERE StudentCourseRelation.CourseId = :courseId")
    fun getStudentsByCourse(courseId: Int): LiveData<List<StudentCourseRelation>>

    @Query("SELECT * FROM studentcourserelation WHERE StudentCourseRelation.StudentId = :studentId")
    fun getCoursesByStudent(studentId: Int): LiveData<List<StudentCourseRelation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudentCourseRelation(studentCourseRelation: StudentCourseRelation)

    @Delete
    suspend fun deleteStudentCourseRelation(studentCourseRelation: StudentCourseRelation)
}