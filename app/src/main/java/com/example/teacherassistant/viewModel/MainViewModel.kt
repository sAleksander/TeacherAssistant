package com.example.teacherassistant.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.teacherassistant.Model.*
import com.example.teacherassistant.Model.Repositories.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var StudentEdit = false
    lateinit var SelectedStudent: Student
    var CourseEdit = false
    lateinit var SelectedCourse: Course
    var GradeEdit = false
    lateinit var SelectedGrade:Grade
    var ToDoEdit = false
    lateinit var SelectedToDo:ToDo

    val courses: LiveData<List<Course>>
    private val courseRepository: CourseRepository
    val students: LiveData<List<Student>>
    private val studentRepository: StudentRepository
    private val studentCourseRelationRepository: StudentCourseRelationRepository
    private val gradeRepository: GradeRepository
    val toDos: LiveData<List<ToDo>>
    private val toDoRepository: ToDoRepository



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

        gradeRepository = GradeRepository(
            TeacherAssistantDatabase.getDatabase(application).GradeDAO()
        )

        toDos = TeacherAssistantDatabase.getDatabase(application).ToDoDAO().getAllToDos()
        toDoRepository = ToDoRepository(TeacherAssistantDatabase.getDatabase(application).ToDoDAO())
    }

    fun addToDo(title:String, description: String){
        viewModelScope.launch {
            toDoRepository.insert(ToDo(Title = title, Description = description))
        }
    }

    fun updateSelectedToDo(){
        viewModelScope.launch {
            toDoRepository.update(SelectedToDo)
        }
    }

    fun deleteSelectedToDo(){
        viewModelScope.launch {
            toDoRepository.delete(SelectedToDo)
        }
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
            studentCourseRelationRepository.insert(studentId, courseId)
        }
    }

    fun getStudentListBySelectedCourse(): LiveData<List<Student>> {
        return studentCourseRelationRepository.getStudentListByCourse(SelectedCourse.Id)
    }

    fun getCourseListBySelectedStudent(): LiveData<List<Course>>{
        return studentCourseRelationRepository.getCourseListByStudent(SelectedStudent.Id)
    }

    fun getGradesByStudentAndCourse(): LiveData<List<Grade>> {
        return gradeRepository.getGradeByCourseAndStudent(SelectedCourse.Id, SelectedStudent.Id)
    }

    fun addGrade(grade: Int, description: String = "") {
        viewModelScope.launch {
            gradeRepository.insert(
                Grade(
                    StudentId = SelectedStudent.Id,
                    CourseId = SelectedCourse.Id,
                    grade = grade,
                    description = description
                )
            )
        }
    }

    fun updateSelectedGrade(){
        viewModelScope.launch {
            gradeRepository.update(SelectedGrade)
        }
    }

    fun deleteSelectedGrade(){
        viewModelScope.launch {
            gradeRepository.delete(SelectedGrade)
        }
    }

    fun getRecentGrades():LiveData<List<Grade>>{
        return gradeRepository.getRecentGrades()
    }
}