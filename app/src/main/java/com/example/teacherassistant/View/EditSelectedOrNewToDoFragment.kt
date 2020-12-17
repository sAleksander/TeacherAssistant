package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_edit_selected_or_new_to_do.*

class EditSelectedOrNewToDoFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_edit_selected_or_new_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.ToDoEdit) {
            editTitle.setText(viewModel.SelectedToDo.Title.toString())
            editDescription.setText(viewModel.SelectedToDo.Description.toString())
        } else {
            editTitle.setText("")
            deleteToDoBtn.isEnabled = false
        }

        deleteToDoBtn.setOnClickListener {
            viewModel.deleteSelectedToDo()
            findNavController().popBackStack()
        }

        submitToDoBtn.setOnClickListener {
            if(viewModel.ToDoEdit){
                viewModel.SelectedToDo.Title = editTitle.text.toString()
                viewModel.SelectedToDo.Description = editDescription.text.toString()
            } else {
                viewModel.addToDo(
                    editTitle.text.toString(),
                    editDescription.text.toString()
                )
            }
            findNavController().popBackStack()
        }
    }
}