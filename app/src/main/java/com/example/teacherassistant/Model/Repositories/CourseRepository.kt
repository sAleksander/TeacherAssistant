package com.example.teacherassistant.Model.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.DAOs.CourseDAO
import kotlinx.coroutines.flow.Flow

class CourseRepository(private val courseDAO: CourseDAO) {
    val allCourses: LiveData<List<Course>> = courseDAO.getAlphabetizedCourseNames()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(course: Course){
        courseDAO.inserCourse(course)
    }
}