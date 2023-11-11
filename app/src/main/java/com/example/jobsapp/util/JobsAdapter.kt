package com.example.jobsapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsapp.data.models.Job
import com.example.jobsapp.databinding.JobListItemBinding

class JobsAdapter(
    var dataset: List<Job>,


) : RecyclerView.Adapter<JobsAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: JobListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            JobListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Dataset
        val item = dataset[position]

        holder.binding.berufTV.text = item.beruf
        holder.binding.arbeitgeberTV.text = item.arbeitgeber
        holder.binding.veroeffentlichungsDatumTV.text = item.aktuelleVeroeffentlichungsdatum


    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}