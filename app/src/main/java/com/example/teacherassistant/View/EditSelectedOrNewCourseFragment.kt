package com.example.teacherassistant.View

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_edit_selected_or_new_course.*

class EditSelectedOrNewCourseFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_edit_selected_or_new_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.CourseEdit) {
            editCourseTextView.setText(viewModel.SelectedCourse.Name.toString())
            deleteCourseBtn.isEnabled = true
        } else {
            editCourseTextView.setText("")
            deleteCourseBtn.isEnabled = false
        }

        deleteCourseBtn.setOnClickListener {
            viewModel.deleteSelectedCourse()
            findNavController().popBackStack()
        }

        submitCourseBtn.setOnClickListener {
            if (viewModel.CourseEdit) {
                viewModel.SelectedCourse.Name = editCourseTextView.text.toString()
                viewModel.updateSelectedCourse()
            } else {
                viewModel.addCourse(editCourseTextView.text.toString())
            }
            findNavController().popBackStack()
        }

    }

}