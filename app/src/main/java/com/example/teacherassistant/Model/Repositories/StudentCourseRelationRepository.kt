package com.example.teacherassistant.Model.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.DAOs.StudentCourseRelationDAO
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseRelation

class StudentCourseRelationRepository(private val studentCourseRelationDAO: StudentCourseRelationDAO) {

    fun getStudentByCourse(courseId:Int): LiveData<List<StudentCourseRelation>>{
        return studentCourseRelationDAO.getStudentsByCourse(courseId)
    }

    fun getCourseByStudent(studentId:Int): LiveData<List<StudentCourseRelation>>{
        return studentCourseRelationDAO.getCoursesByStudent(studentId)
    }

    fun getStudentListByCourse(selectedCourseId:Int):LiveData<List<Student>>{
        return studentCourseRelationDAO.getStudentListByCourse(selectedCourseId)
    }

    fun getCourseListByStudent(selectedStudentId:Int):LiveData<List<Course>>{
        return studentCourseRelationDAO.getCourseListByStudent(selectedStudentId)
    }

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(studentId: Int, courseId: Int){
        studentCourseRelationDAO.insertStudentCourseRelation(StudentCourseRelation(StudentId = studentId, CourseId = courseId))
    }

    @WorkerThread
    suspend fun delete(studentCourseRelation: StudentCourseRelation){
        studentCourseRelationDAO.deleteStudentCourseRelation(studentCourseRelation)
    }
}