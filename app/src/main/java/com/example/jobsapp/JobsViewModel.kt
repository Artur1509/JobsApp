package com.example.jobsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsapp.data.models.jobdetail.JobDetail
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

    // Livedata um aktuelles Jobdetail abzuspeichern damit es im Detail Fragment abgerufen werden kann.
    private val _jobDetail: MutableLiveData<JobDetail> = MutableLiveData()
    val jobDetail: LiveData<JobDetail>
        get() = _jobDetail

    // lade jobdetail
    fun loadJobDetails(encodedHashID: String) {
        viewModelScope.launch {
            try {
                val jobDetail = repository.getJobDetails(encodedHashID)
                _jobDetail.postValue(jobDetail)

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