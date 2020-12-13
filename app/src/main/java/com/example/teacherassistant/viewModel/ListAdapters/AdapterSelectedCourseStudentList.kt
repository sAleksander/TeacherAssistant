package com.example.teacherassistant.viewModel.ListAdapters

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
import kotlinx.android.synthetic.main.fragment_selected_course_student_list.view.*
import kotlinx.android.synthetic.main.recycler_single_name_item.view.*

class AdapterSelectedCourseStudentList(
    private val viewModel: MainViewModel,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<AdapterSelectedCourseStudentList.SelectedCourseStudentViewHolder>() {
    var studentsAsignedToCourse: List<Student> = listOf()

    inner class SelectedCourseStudentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textView = itemView.itemSingleLineText

        init {
            itemView.setOnClickListener {
                viewModel.SelectedStudent = studentsAsignedToCourse[adapterPosition]
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedCourseStudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_single_name_item, parent, false)
        return SelectedCourseStudentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentsAsignedToCourse.size
    }

    override fun onBindViewHolder(holder: SelectedCourseStudentViewHolder, position: Int) {
        val currentItem = studentsAsignedToCourse[position]
        holder.textView.setText(currentItem.FirstName.toString()+" "+currentItem.LastName.toString())
    }

}