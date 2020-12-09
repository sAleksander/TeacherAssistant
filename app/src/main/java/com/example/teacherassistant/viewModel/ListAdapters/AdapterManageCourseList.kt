package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_single_name_item.view.*

class AdapterManageCourseList(
    var courseList: LiveData<List<Course>>,
    private val onClick: (position: Int) -> Unit,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<AdapterManageCourseList.ManageCourseViewHolder>() {
    inner class ManageCourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.itemSingleLineText

        init {
            itemView.setOnClickListener {
                viewModel.CourseEdit = true
                viewModel.SelectedCourse = courseList.value?.get(adapterPosition)!!
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageCourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_single_name_item, parent, false)
        return ManageCourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return courseList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ManageCourseViewHolder, position: Int) {
        val currentItem = courseList.value?.get(position)
        if (currentItem != null) {
            holder.textView.setText(currentItem.Name.toString())
        }
    }
}