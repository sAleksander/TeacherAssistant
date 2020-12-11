package com.example.teacherassistant.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.Model.DAOs.CourseDAO
import com.example.teacherassistant.Model.DAOs.StudentCourseRelationDAO
import com.example.teacherassistant.Model.DAOs.StudentDAO

@Database(entities = [Course::class, Student::class, StudentCourseRelation::class], version = 1, exportSchema = false)
public abstract class TeacherAssistantDatabase : RoomDatabase() {

    abstract fun CourseDAO(): CourseDAO
    abstract fun StudentDAO(): StudentDAO
    abstract fun StudentCourseRelationDAO(): StudentCourseRelationDAO

    companion object{
        @Volatile
        private var INSTANCE: TeacherAssistantDatabase? = null
        fun getDatabase(context: Context): TeacherAssistantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeacherAssistantDatabase::class.java,
                    "teacher_assistant_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}