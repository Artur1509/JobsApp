package com.example.jobsapp.remote

import com.example.jobsapp.data.models.JobList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://rest.arbeitsagentur.de/"

val client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->

    val newRequest: Request = chain.request().newBuilder()
        .addHeader("X-API-Key", "c003a37f-024f-462a-b36d-b001be4cd24a")
        .build()

    chain.proceed(newRequest)

}.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JobsucheApiService {
    @GET("jobboerse/jobsuche-service/pc/v4/app/jobs")
    suspend fun getJobs(): JobList
}

object JobsApi {
    val retrofitService: JobsucheApiService by lazy { retrofit.create(JobsucheApiService::class.java) }
}
