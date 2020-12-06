package com.example.teacherassistant.viewModel

import androidx.lifecycle.*
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(private val repository: CourseRepository):ViewModel() {
    val allCourses: LiveData<List<Course>> = repository.allCourses.asLiveData()

    fun insert(course: Course) = viewModelScope.launch {
        repository.insert(course)
    }
}

class CourseViewModelFactory(private val respository: CourseRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(respository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}