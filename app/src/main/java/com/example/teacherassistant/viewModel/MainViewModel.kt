package com.example.teacherassistant.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Repositories.CourseRepository
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.TeacherAssistantDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {
    var SelectedCourseName = ""
    var SelectedStudentName = ""
    var StudentEdit = false
    lateinit var SelectedStudent: Student
    var CourseEdit = false
    lateinit var SelectedCourse: Course

    val courses:LiveData<List<Course>>
    private val courseRepository:CourseRepository

    init {
        courses = TeacherAssistantDatabase.getDatabase(application).CourseDAO().getAllCourses()
        courseRepository = CourseRepository(TeacherAssistantDatabase.getDatabase(application).CourseDAO())
    }

    fun addCourse(name:String){
        viewModelScope.launch {
            courseRepository.insert(Course(Name=name))
        }
    }

    fun deleteSelectedCourse(){
        viewModelScope.launch {
            courseRepository.delete(SelectedCourse)
        }
    }
}