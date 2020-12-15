package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.ListAdapters.AdapterSelectedStudentGradeList
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_selected_student_grade_list.*

class SelectedStudentGradeListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: AdapterSelectedStudentGradeList
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    // TODO: 12/13/2020 ustawić recycler i adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val onItemclick = { position: Int ->
            view?.findNavController()
                ?.navigate(R.id.action_selectedStudentGradeListFragment_to_editSelectedOrNewGradeFragment)
        }

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        myAdapter = AdapterSelectedStudentGradeList(viewModel, onItemclick as (Int) -> Unit)

        viewModel.getGradesByStudentAndCourse().observe(viewLifecycleOwner, Observer {
            myAdapter.gradesAsignedToStudentAndCourse = it
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_selected_student_grade_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseNameDisplayer.setText(viewModel.SelectedCourse.Name.toString())
        studentNameDisplayer.setText(viewModel.SelectedStudent.FirstName.toString() + " " + viewModel.SelectedStudent.LastName.toString())

        addGradeBtn.setOnClickListener{
            viewModel.GradeEdit = false
            findNavController().navigate(R.id.action_selectedStudentGradeListFragment_to_editSelectedOrNewGradeFragment)
        }

        recyclerView = recyclerSelectedStudentGradeList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}