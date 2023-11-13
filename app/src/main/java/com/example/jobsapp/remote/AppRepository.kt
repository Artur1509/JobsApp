package com.example.jobsapp.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobsapp.data.models.Job
import com.example.jobsapp.data.models.jobdetail.JobDetail
import retrofit2.HttpException

class AppRepository(private val api: JobsApi) {

    private val _jobs = MutableLiveData<List<Job>>()

    val jobs: LiveData<List<Job>>
        get() = _jobs

    // Lade Jobs
    suspend fun getJobs() {
        try {
            val jobList = api.retrofitService.getJobs()
            Log.d("AppRepository", jobList.toString())
            _jobs.value = jobList.stellenangebote!!
        } catch (e: Exception) {
            Log.e("AppRepository", "Error loading data from API: $e")
        }
    }
    // Lade Jobdetails
    suspend fun getJobDetails(encodedHashID: String): JobDetail {
        try {
            val jobDetail = api.retrofitService.getJobDetails(encodedHashID)
            Log.d("AppRepository", jobDetail.toString())
            return jobDetail
        } catch (e: HttpException) {
            Log.e("AppRepository", "HTTP error loading job details: ${e.code()} ${e.message()}")
            throw e
        } catch (e: Exception) {
            Log.e("AppRepository", "Error loading job details from API: $e")
            throw e
        }
    }

    // Lade Jobs nach Berufsfeld
    suspend fun getJobsByJobField(berufsfeld: String) {
        try {
            val jobList = api.retrofitService.getJobsBy(berufsfeld = berufsfeld)
            Log.d("AppRepository", jobList.toString())
            _jobs.value = jobList.stellenangebote!!
        } catch (e: Exception) {
            Log.e("AppRepository", "Error loading data from API: $e")
        }
    }
}