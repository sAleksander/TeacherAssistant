package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDAO {
    @Query("SELECT * FROM Course ORDER BY Name ASC")
    fun getAllCourses(): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserCourse(course:Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)
}
