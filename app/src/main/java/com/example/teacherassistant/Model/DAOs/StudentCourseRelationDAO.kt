package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseRelation

@Dao
interface StudentCourseRelationDAO {
    @Query("SELECT * FROM StudentCourseRelation WHERE StudentCourseRelation.CourseId = :courseId")
    fun getStudentsByCourse(courseId: Int): LiveData<List<StudentCourseRelation>>

    @Query("SELECT * FROM Studentcourserelation WHERE StudentCourseRelation.StudentId = :studentId")
    fun getCoursesByStudent(studentId: Int): LiveData<List<StudentCourseRelation>>

    @Query("SELECT Student.* FROM Student, StudentCourseRelation WHERE Student.Id = StudentCourseRelation.StudentId AND StudentCourseRelation.CourseId = :selectedCourseId")
    fun getStudentListByCourse(selectedCourseId:Int): LiveData<List<Student>>

    @Query("SELECT Course.* FROM Course, StudentCourseRelation WHERE course.Id = StudentCourseRelation.CourseId AND StudentCourseRelation.StudentId = :selectedStudentId")
    fun getCourseListByStudent(selectedStudentId:Int): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudentCourseRelation(studentCourseRelation: StudentCourseRelation)

    @Delete
    suspend fun deleteStudentCourseRelation(studentCourseRelation: StudentCourseRelation)
}