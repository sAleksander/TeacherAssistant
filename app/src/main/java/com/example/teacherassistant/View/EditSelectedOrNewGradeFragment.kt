package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_edit_selected_or_new_course.*
import kotlinx.android.synthetic.main.fragment_edit_selected_or_new_grade.*
import kotlinx.android.synthetic.main.fragment_selected_student_grade_list.*
import kotlinx.android.synthetic.main.fragment_selected_student_grade_list.courseNameDisplayer
import kotlinx.android.synthetic.main.fragment_selected_student_grade_list.studentNameDisplayer

class EditSelectedOrNewGradeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_edit_selected_or_new_grade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseNameDisplayer.setText(viewModel.SelectedCourse.Name.toString())
        studentNameDisplayer.setText(viewModel.SelectedStudent.FirstName.toString() + " " + viewModel.SelectedStudent.LastName.toString())

        if (viewModel.GradeEdit) {
            editGrade.setText(viewModel.SelectedGrade.getGrade().toString())
            editDescription.setText(viewModel.SelectedGrade.description.toString())
            deleteGradeBtn.isEnabled = true
        } else {
            editGrade.setText("")
            editDescription.setText("")
            deleteGradeBtn.isEnabled = false
        }

        deleteGradeBtn.setOnClickListener {
            viewModel.deleteSelectedGrade()
            findNavController().popBackStack()
        }

        submitGradeBtn.setOnClickListener {
            if (viewModel.GradeEdit) {
                viewModel.SelectedGrade.setGrade(editGrade.text.toString().toInt())
                viewModel.SelectedGrade.description = editDescription.toString()
                viewModel.updateSelectedGrade()
            } else {
               viewModel.addGrade(
                   editGrade.text.toString().toInt(),
                   editDescription.text.toString()
               )
            }
            findNavController().popBackStack()
        }


    }
}