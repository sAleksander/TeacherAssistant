package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_edit_student_item.view.*

class AdapterManageStudentList(
    var studentList: LiveData<List<Student>>,
    private val onTextClick: (position: Int) -> Unit,
    private val onButtonClick: (position: Int) -> Unit,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<AdapterManageStudentList.ManageStudentViewHolder>() {
    inner class ManageStudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.itemNameTextView
        val button = itemView.goEditItem

        init {
            textView.setOnClickListener {
                viewModel.SelectedStudent = studentList.value?.get(adapterPosition)!!
                onTextClick(adapterPosition)
            }
            button.setOnClickListener {
                viewModel.StudentEdit = true
                viewModel.SelectedStudent = studentList.value?.get(adapterPosition)!!
                onButtonClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageStudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_edit_student_item, parent, false)
        return ManageStudentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentList?.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ManageStudentViewHolder, position: Int) {
        val currentItem = studentList.value?.get(position)
        if (currentItem != null) {
            holder.textView.setText(currentItem.FirstName.toString() + " " + currentItem.LastName.toString())
        }
    }


}