package com.example.jobsapp.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobsapp.data.models.Job
import com.example.jobsapp.remote.JobsApi

class AppRepository(private val api: JobsApi) {

    private val _jobs = MutableLiveData<List<Job>>()

    val jobs: LiveData<List<Job>>
        get() = _jobs

    suspend fun getJobs() {
        try {
            val jobList = api.retrofitService.getJobs()
            Log.d("API Response", jobList.toString())
            _jobs.value = jobList.stellenangebote!!
        } catch (e: Exception) {
            Log.e("AppRepository", "Error loading data from API: $e")
        }
    }
}