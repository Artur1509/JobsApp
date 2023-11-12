package com.example.jobsapp

import android.util.Log
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

    fun loadJobDetails(encodedHashID: String) {
        viewModelScope.launch {
            try {
                val jobDetail = repository.getJobDetails(encodedHashID)

            } catch (e: Exception) {
                Log.e("JobsViewModel", "Error loading job details: $e")
            }
        }
    }
}