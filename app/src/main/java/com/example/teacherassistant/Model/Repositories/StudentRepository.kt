package com.example.teacherassistant.Model.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.DAOs.StudentDAO
import com.example.teacherassistant.Model.Student

class StudentRepository(private val studentDAO:StudentDAO) {
    val allStudents: LiveData<List<Student>> = studentDAO.getAllStudents()

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(student: Student){
        studentDAO.insertStudent(student)
    }

    @WorkerThread
    suspend fun update(student: Student){
        studentDAO.updateStudent(student)
    }

    @WorkerThread
    suspend fun delete(student: Student){
        studentDAO.deleteStudent(student)
    }
}