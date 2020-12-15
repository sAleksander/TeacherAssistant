package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_single_name_item.view.*

class AdapterSelectedStudentCourseList(
    private val viewModel: MainViewModel,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<AdapterSelectedStudentCourseList.SelectedStudentCourseViewHolder>() {
    var coursesAsignedToStudent: List<Course> = listOf()

    inner class SelectedStudentCourseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textView = itemView.itemSingleLineText

        init {
            itemView.setOnClickListener {
                viewModel.SelectedCourse = coursesAsignedToStudent[adapterPosition]
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedStudentCourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_single_name_item, parent, false)
        return SelectedStudentCourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return coursesAsignedToStudent.size
    }

    override fun onBindViewHolder(holder: SelectedStudentCourseViewHolder, position: Int) {
        val currentItem = coursesAsignedToStudent[position]
        holder.textView.setText(currentItem.Name.toString())
    }
}