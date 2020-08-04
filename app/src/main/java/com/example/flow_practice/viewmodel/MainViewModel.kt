package com.example.flow_practice.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.flow_practice.network.Blogs
import com.example.flow_practice.network.NetworkService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class MainViewModel
@ViewModelInject
constructor(
    private val networkService: NetworkService
    ) : ViewModel() {
    private var mediatorLiveData : MediatorLiveData<DataState<List<Blogs>>> = MediatorLiveData()
    private var TAG: String = "MainViewModel -"
    private lateinit var job: Job

    val data: LiveData<DataState<List<Blogs>>>
        get() = mediatorLiveData

    fun loadData(statusReport: StatusReport){
        Log.d(TAG, "loadData: started")
        viewModelScope.launch {
            when(statusReport){
                is StatusReport.FetchDataFromNetwork -> fetchData().onEach {
                    dataState -> mediatorLiveData.value = dataState
                    Log.d(TAG, "loadData: data - $dataState")
                }.launchIn(viewModelScope)
            }
        }
    }

    suspend fun fetchData(): Flow<DataState<List<Blogs>>> = flow {
        val result = networkService.getBlogs()
        emit(DataState.Success(result))
    }

    sealed class StatusReport{
        object FetchDataFromNetwork : StatusReport()
        object Nothing : StatusReport()
    }

    sealed class DataState<out T>{
        data class Success<out T>(val data: T) : DataState<T>()
        data class Error(val exception: Exception): DataState<Nothing>()
    }

}