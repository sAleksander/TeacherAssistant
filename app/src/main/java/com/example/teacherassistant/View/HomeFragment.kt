package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goCourseList.setOnClickListener { view -> view.findNavController().navigate(R.id.action_homeFragment_to_courseListFragment) }
        goManageStudentList.setOnClickListener { view -> view.findNavController().navigate(R.id.action_homeFragment_to_manageStudentListFragment) }
        goManageCourseList.setOnClickListener { view -> view.findNavController().navigate(R.id.action_homeFragment_to_manageCourseListFragment) }
        goTodaysActivities.setOnClickListener { view -> view.findNavController().navigate(R.id.action_homeFragment_to_todaysActivitiesFragment) }
    }
}