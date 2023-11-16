package com.example.jobsapp.util

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsapp.JobsViewModel
import com.example.jobsapp.data.models.Job
import com.example.jobsapp.databinding.JobListItemBinding
import com.example.jobsapp.ui.JobsFragmentDirections
import java.nio.charset.StandardCharsets
import java.util.Base64

class JobsAdapter(
    var dataset: List<Job>,
    var viewModel: JobsViewModel


) : RecyclerView.Adapter<JobsAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: JobListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            JobListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Dataset
        val item = dataset[position]

        holder.binding.berufTV.text = item.beruf
        holder.binding.arbeitgeberTV.text = item.arbeitgeber
        holder.binding.veroeffentlichungsDatumTV.text = formatReleaseDate(item.aktuelleVeroeffentlichungsdatum)
        holder.binding.strasseTV.text = item.arbeitsort.strasse
        holder.binding.plzTV.text = item.arbeitsort.plz.toString()
        holder.binding.ortTV.text = item.arbeitsort.ort
        holder.binding.regionTV.text = item.arbeitsort.region
        holder.binding.landTV.text = item.arbeitsort.land

        holder.itemView.setOnClickListener {
            val navController = holder.itemView.findNavController()

            // Refnr muss erst in Base64 encoded werden, damit der apicall funktioniert um das Jobdetail zu sehen !
            val originalString = item.refnr
            val encodedBytes = Base64.getEncoder().encode(originalString.toByteArray(StandardCharsets.UTF_8))
            val encodedString = String(encodedBytes)

            viewModel.loadJobDetails(encodedString)
            navController.navigate(JobsFragmentDirections.actionJobsFragmentToJobDetailFragment())

        }

    }
    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun formatReleaseDate(releaseDate: String): String {
        releaseDate.split("-").also {
            return "${it[2]}.${it[1]}.${it[0]}"
        }
    }

}