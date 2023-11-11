package com.example.jobsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsapp.remote.AppRepository
import com.example.jobsapp.remote.JobsApi
import kotlinx.coroutines.launch

class JobsViewModel : ViewModel() {

    private val repository = AppRepository(JobsApi)

    val jobs = repository.jobs

    fun loadData() {
        viewModelScope.launch {
            repository.getJobs()
        }
    }
}