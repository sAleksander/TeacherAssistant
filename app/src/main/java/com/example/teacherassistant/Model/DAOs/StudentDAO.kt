package com.example.teacherassistant.Model.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.Model.Student

@Dao
interface StudentDAO {
    @Query("SELECT * FROM Student ORDER BY LastName ASC")
    fun getAlphabetizedStudents(): LiveData<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student:Student)
}