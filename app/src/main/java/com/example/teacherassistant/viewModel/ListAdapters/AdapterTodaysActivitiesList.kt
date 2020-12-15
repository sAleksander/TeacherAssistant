package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_recent_activity_item.view.*

class AdapterTodaysActivitiesList(
    private val viewModel: MainViewModel,
    private val studentList: LiveData<List<Student>>,
    private val courseList: LiveData<List<Course>>
) : RecyclerView.Adapter<AdapterTodaysActivitiesList.TodaysActivitiesViewHolder>() {
    var recentGrades: List<Grade> = listOf()

    inner class TodaysActivitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gradeTextView = itemView.displayGrade
        val courseNameTextView = itemView.displayCourseName
        val studentFirstNameTextView = itemView.displayStudentName
        val studentLastNameTextView = itemView.displayStudentSurname
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaysActivitiesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_recent_activity_item, parent, false)
        return TodaysActivitiesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recentGrades.size
    }

    override fun onBindViewHolder(holder: TodaysActivitiesViewHolder, position: Int) {
        val currentItem = recentGrades[position]
        val studentItem = getStudent(currentItem.StudentId)
        val courseItem = getCourse(currentItem.CourseId)

        holder.gradeTextView.setText(currentItem.getGrade().toString())
        holder.courseNameTextView.setText(courseItem.Name.toString())
        holder.studentFirstNameTextView.setText(studentItem.FirstName.toString())
        holder.studentLastNameTextView.setText(studentItem.LastName.toString())
    }

    fun getStudent(studentId:Int):Student {
        val no = Student(-1,"","")
        for (el in studentList.value!!) if (el.Id == studentId) return el
        return no
    }

    fun getCourse(courseId:Int):Course {
        val no = Course(-1,"")
        for (el in courseList.value!!) if (el.Id == courseId) return el
        return no
    }


}