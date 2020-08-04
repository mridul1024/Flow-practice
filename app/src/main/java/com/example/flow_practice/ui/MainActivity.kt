package com.example.flow_practice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_practice.R
import com.example.flow_practice.utils.ListAdapter
import com.example.flow_practice.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
//    private lateinit var recyclerView : RecyclerView
    private var TAG: String = "MainActivity-"
    private lateinit var recyclerViewAdapter : RecyclerView.Adapter<*>
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeObserver()
        viewModel.loadData(MainViewModel.StatusReport.FetchDataFromNetwork)
        initUi()
    }

    fun initializeObserver(){
        Log.d(TAG, "initializeObserver: inside init observer")
        viewModel.data.observe(this, Observer {
            dataState -> when(dataState){
            is MainViewModel.DataState.Success -> {
             recyclerViewAdapter = ListAdapter(dataState.data)
                swapAdapter(recyclerViewAdapter as ListAdapter)
            }
        }
        })
    }

    fun initUi(){
        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.apply{
//            layoutManager = linearLayoutManager
//            adapter = recyclerViewAdapter
//        }
        recycler_view.apply {
            layoutManager = linearLayoutManager
            adapter = ListAdapter()
        }
    }

    fun swapAdapter(adapter: ListAdapter){
        recycler_view.adapter = adapter
    }
}