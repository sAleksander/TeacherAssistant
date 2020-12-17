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
import com.example.teacherassistant.viewModel.ListAdapters.AdapterToDoList
import com.example.teacherassistant.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoList : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: AdapterToDoList
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

        val innerOnClick = { position: Int ->
            view?.findNavController()?.navigate(R.id.action_toDoList2_to_editSelectedOrNewToDoFragment2)
        }

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        myAdapter = AdapterToDoList(viewModel.toDos, innerOnClick as (Int) -> Unit, viewModel)

        viewModel.toDos.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goEditItem.setOnClickListener {
            viewModel.ToDoEdit = false
            view.findNavController().navigate(R.id.action_toDoList2_to_editSelectedOrNewToDoFragment2)
        }

        recyclerView = recyclerToDoList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}