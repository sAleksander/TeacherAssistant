package com.example.teacherassistant.Model.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.DAOs.GradeDAO
import com.example.teacherassistant.Model.Grade

class GradeRepository(private val gradeDAO: GradeDAO) {
    fun getGradeByCourseAndStudent(courseId: Int, studentId: Int): LiveData<List<Grade>>{
        return gradeDAO.getOrderedGrades(studentId,courseId)
    }

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(grade: Grade){
        gradeDAO.insertGrade(grade)
    }

    @WorkerThread
    suspend fun update(grade: Grade){
        gradeDAO.updateGrade(grade)
    }

    @WorkerThread
    suspend fun delete(grade: Grade){
       gradeDAO.deleteGrade(grade)
    }
}