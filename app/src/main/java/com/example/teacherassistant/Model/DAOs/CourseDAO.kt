package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.Model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDAO {
    @Query("SELECT * FROM Course ORDER BY Name ASC")
    fun getAlphabetizedCourseNames(): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserCourse(course:Course)
}
