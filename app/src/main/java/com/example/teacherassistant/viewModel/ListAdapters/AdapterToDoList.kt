package com.example.teacherassistant.viewModel.ListAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.ToDo
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.recycler_single_name_item.view.*

class AdapterToDoList(
    var toDoList: LiveData<List<ToDo>>,
    private val onClick: (position: Int) -> Unit,
    private val viewModel: MainViewModel
): RecyclerView.Adapter<AdapterToDoList.ToDoViewHolder>() {
    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.itemSingleLineText

        init {
            itemView.setOnClickListener {
                viewModel.ToDoEdit = true
                viewModel.SelectedToDo = toDoList.value?.get(adapterPosition)!!
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_name_item,parent,false)
        return ToDoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return toDoList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = toDoList.value?.get(position)
        if (currentItem != null) {
            holder.textView.setText(currentItem.Title.toString())
        }
    }
}