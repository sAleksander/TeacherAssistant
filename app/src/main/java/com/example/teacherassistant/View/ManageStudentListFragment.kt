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
import com.example.teacherassistant.viewModel.ListAdapters.AdapterManageCourseList
import com.example.teacherassistant.viewModel.ListAdapters.AdapterManageStudentList
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_manage_student_list.*

class ManageStudentListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: AdapterManageStudentList
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

        val textViewClick = { position: Int ->
            view?.findNavController()
                ?.navigate(R.id.action_manageStudentListFragment_to_selectedStudentCourseListFragment)
        }
        val buttonClick = { position: Int ->
            view?.findNavController()
                ?.navigate(R.id.action_manageStudentListFragment_to_editSelectedOrNewStudentFragment)
        }

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        myAdapter = AdapterManageStudentList(
            viewModel.students, textViewClick as (Int) -> Unit,
            buttonClick as (Int) -> Unit, viewModel
        )

        viewModel.students.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_manage_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goEditItem.setOnClickListener { view ->
            viewModel.StudentEdit = false;
            view.findNavController().navigate(R.id.action_manageStudentListFragment_to_editSelectedOrNewStudentFragment)
        }

        recyclerView = recyclerManageStudentList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}