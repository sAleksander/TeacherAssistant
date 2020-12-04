package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R
import kotlinx.android.synthetic.main.recycler_single_name_item.view.*

class AdapterCourseList(
    private val courseList: LiveData<ArrayList<Course>>,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<AdapterCourseList.CourseViewHolder>() {
    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val textView: TextView = itemView.itemSingleLineText
        init {
            itemView.setOnClickListener { onClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_name_item,parent,false)
        return CourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return courseList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentitem = courseList.value?.get(position)
        if (currentitem != null) {
            holder.textView.setText(currentitem.Name.toString())
        }
    }

}