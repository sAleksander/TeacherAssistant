package com.example.teacherassistant.viewModel.ListAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseRelation
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_edit_student_item.view.*

class AdapterCourseSelectionList(
    var courseList: LiveData<List<Course>>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<AdapterCourseSelectionList.CourseSelectionViewHolder>() {
    var studentCourseRelation: List<StudentCourseRelation> = listOf()

    inner class CourseSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.itemNameTextView
        val button = itemView.goEditItem

        init {
            button.setOnClickListener {
                if (viewModel.StudentEdit) {
                    if (button.text.toString() == "+") {
                        button.setText("-")
                        courseList.value?.get(adapterPosition)?.Id?.let { it1 ->
                            viewModel.addStudentCourseRelation(
                                viewModel.SelectedStudent.Id,
                                it1
                            )
                        }
                    } else {
                        button.setText("+")
                        viewModel.deleteStudentCourseRelation(
                            getStudentCourseRelation(
                                viewModel.SelectedStudent.Id,
                                courseList.value!!.get(adapterPosition).Id
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseSelectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_edit_student_item, parent, false)
        return CourseSelectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (viewModel.StudentEdit) return courseList?.value?.size ?: 0
        else return 0
    }

    override fun onBindViewHolder(holder: CourseSelectionViewHolder, position: Int) {
        val currentItem = courseList.value?.get(position)
        if (currentItem != null) {
            if (viewModel.StudentEdit) {
                holder.textView.setText(currentItem.Name.toString())
                if (studentInCourse(currentItem.Id)) holder.button.setText("-")
                else holder.button.setText("+")
            }
        }
    }

    fun studentInCourse(courseId: Int): Boolean {
            for (el in studentCourseRelation) {
                if (el.CourseId == courseId) return true
            }
        return false
    }

    fun getStudentCourseRelation(studentId: Int, courseId: Int): StudentCourseRelation {
        var tmp = StudentCourseRelation(-1, -1, -1)
        for (el in studentCourseRelation) {
            if ((el.CourseId == courseId) && (el.StudentId == studentId)) tmp = el
        }
        return tmp
    }
}