package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.ListAdapters.AdapterSelectedCourseStudentList
import com.example.teacherassistant.viewModel.ListAdapters.AdapterSelectedStudentCourseList
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_selected_student_course_list.*

class SelectedStudentCourseListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: AdapterSelectedStudentCourseList
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
                ?.navigate(R.id.action_selectedStudentCourseListFragment_to_selectedStudentGradeListFragment)
        }

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        myAdapter = AdapterSelectedStudentCourseList(
            viewModel,
            onItemClick as (Int) -> Unit
        )

        viewModel.students.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        viewModel.getCourseListBySelectedStudent().observe(viewLifecycleOwner, Observer {
            myAdapter.coursesAsignedToStudent = it
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_selected_student_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseNameDisplayer.setText(viewModel.SelectedStudent.FirstName.toString() + " " + viewModel.SelectedStudent.LastName.toString())
        recyclerView = recyclerSelectedStudentCourseList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}