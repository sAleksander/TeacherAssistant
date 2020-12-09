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
import com.example.teacherassistant.viewModel.ListAdapters.AdapterCourseList
import com.example.teacherassistant.viewModel.ListAdapters.AdapterManageCourseList
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_manage_course_list.*

class ManageCourseListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: AdapterManageCourseList
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val innerOnClick = { position: Int ->
            view?.findNavController()
                ?.navigate(R.id.action_manageCourseListFragment_to_editSelectedOrNewCourseFragment)
        }

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        myAdapter =
            AdapterManageCourseList(viewModel.courses, innerOnClick as (Int) -> Unit, viewModel)

        viewModel.courses.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_manage_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goEditItem.setOnClickListener { view ->
            viewModel.CourseEdit = false
            view.findNavController()
                .navigate(R.id.action_manageCourseListFragment_to_editSelectedOrNewCourseFragment)
        }

        recyclerView = recyclerManageCourseList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}