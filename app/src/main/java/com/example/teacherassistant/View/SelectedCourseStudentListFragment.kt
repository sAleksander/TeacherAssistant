package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.ListAdapters.AdapterSelectedCourseStudentList
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_selected_course_student_list.*

class SelectedCourseStudentListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: AdapterSelectedCourseStudentList
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val onItemClick = { position: Int ->
            view?.findNavController()
                ?.navigate(R.id.action_selectedCourseStudentListFragment_to_selectedStudentGradeListFragment)
        }

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        myAdapter = AdapterSelectedCourseStudentList(
            viewModel,
            onItemClick as (Int) -> Unit
        )

        viewModel.students.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        viewModel.getStudentListBySelectedCourse().observe(viewLifecycleOwner, Observer {
            myAdapter.studentsAsignedToCourse = it
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_selected_course_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseNameDisplayer.setText(viewModel.SelectedCourse.Name.toString())

        recyclerView = recyclerSelectedCourseStudentList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}