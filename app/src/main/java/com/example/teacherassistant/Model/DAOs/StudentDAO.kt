package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Student

@Dao
interface StudentDAO {
    @Query("SELECT * FROM Student ORDER BY LastName ASC")
    fun getAllStudents(): LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student:Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)
}