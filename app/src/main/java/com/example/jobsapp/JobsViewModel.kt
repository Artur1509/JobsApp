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

    // lade jobs
    fun loadData() {
        viewModelScope.launch {
            repository.getJobs()
        }
    }

    // lade jobdetail --- 404 Error, findet keine Daten mit der hashId???
    fun loadJobDetails(encodedHashID: String) {
        viewModelScope.launch {
            try {
                val jobDetail = repository.getJobDetails(encodedHashID)

            } catch (e: Exception) {
                Log.e("JobsViewModel", "Error loading job details: $e")
            }
        }
    }

    // Lade jobs gefiltert nach Berufsfeld.
    fun loadJobsByJobField(berufsfeld: String) {
        viewModelScope.launch {
            repository.getJobsByJobField(berufsfeld)
        }
    }
}