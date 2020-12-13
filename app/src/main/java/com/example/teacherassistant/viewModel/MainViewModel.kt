package com.example.teacherassistant.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Repositories.CourseRepository
import com.example.teacherassistant.Model.Repositories.StudentCourseRelationRepository
import com.example.teacherassistant.Model.Repositories.StudentRepository
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseRelation
import com.example.teacherassistant.Model.TeacherAssistantDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var StudentEdit = false
    lateinit var SelectedStudent: Student
    var CourseEdit = false
    lateinit var SelectedCourse: Course

    val courses: LiveData<List<Course>>
    private val courseRepository: CourseRepository
    val students: LiveData<List<Student>>
    private val studentRepository: StudentRepository
    private val studentCourseRelationRepository: StudentCourseRelationRepository


    init {
        courses = TeacherAssistantDatabase.getDatabase(application).CourseDAO().getAllCourses()
        courseRepository =
            CourseRepository(TeacherAssistantDatabase.getDatabase(application).CourseDAO())

        students = TeacherAssistantDatabase.getDatabase(application).StudentDAO().getAllStudents()
        studentRepository =
            StudentRepository(TeacherAssistantDatabase.getDatabase(application).StudentDAO())

        studentCourseRelationRepository = StudentCourseRelationRepository(
            TeacherAssistantDatabase.getDatabase(application).StudentCourseRelationDAO()
        )
    }

    fun addCourse(name: String) {
        viewModelScope.launch {
            courseRepository.insert(Course(Name = name))
        }
    }

    fun updateSelectedCourse() {
        viewModelScope.launch {
            courseRepository.update(SelectedCourse)
        }
    }

    fun deleteSelectedCourse() {
        viewModelScope.launch {
            courseRepository.delete(SelectedCourse)
        }
    }

    fun addStudent(firstName: String, lastName: String) {
        viewModelScope.launch {
            studentRepository.insert(Student(FirstName = firstName, LastName = lastName))
        }
    }

    fun updateSelectedStudent() {
        viewModelScope.launch {
            studentRepository.update(SelectedStudent)
        }
    }

    fun deleteSelectedStudent() {
        viewModelScope.launch {
            studentRepository.delete(SelectedStudent)
        }
    }

    fun getSelectedCourseStudents(): LiveData<List<StudentCourseRelation>> {
        return studentCourseRelationRepository.getStudentByCourse(SelectedCourse.Id)
    }

    fun getSelectedStudentCourses(): LiveData<List<StudentCourseRelation>> {
        return studentCourseRelationRepository.getCourseByStudent(SelectedStudent.Id)
    }

    fun deleteStudentCourseRelation(studentCourseRelation: StudentCourseRelation) {
        viewModelScope.launch {
            studentCourseRelationRepository.delete(studentCourseRelation)
        }
    }

    fun addStudentCourseRelation(studentId: Int, courseId: Int) {
        viewModelScope.launch {
            studentCourseRelationRepository.insert(studentId,courseId)
        }
    }

    fun getStudentListBySelectedCourse(): LiveData<List<Student>> {
        return studentCourseRelationRepository.getStudentListByCourse(SelectedCourse.Id)
    }
}