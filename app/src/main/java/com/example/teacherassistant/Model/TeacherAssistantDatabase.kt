package com.example.teacherassistant.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.Model.DAOs.CourseDAO

@Database(entities = arrayOf(Course::class), version = 1, exportSchema = false)
public abstract class TeacherAssistantDatabase : RoomDatabase() {

    abstract fun CourseDAO(): CourseDAO

    companion object{
        @Volatile
        private var INSTANCE: TeacherAssistantDatabase? = null
        fun getDatabase(context: Context): TeacherAssistantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeacherAssistantDatabase::class.java,
                    "TeacherAssistantDatabase"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}