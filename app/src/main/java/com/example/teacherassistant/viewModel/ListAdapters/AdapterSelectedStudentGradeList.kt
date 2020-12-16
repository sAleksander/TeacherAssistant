package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_grade_description_item.view.*

class AdapterSelectedStudentGradeList(
    private val viewModel: MainViewModel,
    private val onClick: (position: Int) -> Unit
): RecyclerView.Adapter<AdapterSelectedStudentGradeList.SelectedStudentGradeViewHolder>() {
    var gradesAsignedToStudentAndCourse: List<Grade> = listOf()

    inner class SelectedStudentGradeViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        val gradeTextView = itemView.gradeTextView
        val descriptionTextView = itemView.descriptionTextView

        init {
            itemView.setOnClickListener {
                viewModel.SelectedGrade = gradesAsignedToStudentAndCourse[adapterPosition]
                viewModel.GradeEdit = true
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedStudentGradeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_grade_description_item, parent, false)
        return SelectedStudentGradeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return gradesAsignedToStudentAndCourse.size // razy dwa nie wiem czemu
    }

    override fun onBindViewHolder(holder: SelectedStudentGradeViewHolder, position: Int) {
        val currentItem = gradesAsignedToStudentAndCourse[position]
        holder.gradeTextView.setText(currentItem.getGrade().toString())
        holder.descriptionTextView.setText(currentItem.description.toString())
    }
}