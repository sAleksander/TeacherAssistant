package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.Model.StudentCourseRelation

@Dao
interface StudentCourseRelationDAO {
    @Query("SELECT * FROM StudentCourseRelation WHERE StudentCourseRelation.CourseId = :courseId")
    fun getStudentsByCourse(courseId: Int): LiveData<StudentCourseRelation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudentCourseRelation(studentCourseRelation: StudentCourseRelation)
}