package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_edit_selected_or_new_student.*

class EditSelectedOrNewStudentFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_edit_selected_or_new_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.StudentEdit) {
            editStudentName.setText(viewModel.SelectedStudent.FirstName.toString())
            editStudentSurname.setText(viewModel.SelectedStudent.LastName.toString())
            deleteStudentBtn.isEnabled = true
        } else {
            editStudentName.setText("")
            editStudentSurname.setText("")
            deleteStudentBtn.isEnabled = false
        }

        deleteStudentBtn.setOnClickListener {
            viewModel.deleteSelectedStudent()
            parentFragmentManager.popBackStack()
        }

        submitStudentBtn.setOnClickListener {
            if (viewModel.CourseEdit) {
                viewModel.SelectedStudent.FirstName = editStudentName.text.toString()
                viewModel.SelectedStudent.LastName = editStudentSurname.text.toString()
                viewModel.updateSelectedStudent()
            } else {
                viewModel.addStudent(
                    editStudentName.text.toString(),
                    editStudentSurname.text.toString()
                )
            }
            parentFragmentManager.popBackStack()
            println("Działam!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        }
    }
}