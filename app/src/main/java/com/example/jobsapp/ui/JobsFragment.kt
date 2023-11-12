package com.example.jobsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.jobsapp.JobsViewModel
import com.example.jobsapp.R
import com.example.jobsapp.databinding.FragmentJobsBinding
import com.example.jobsapp.util.JobsAdapter

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private val viewModel: JobsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.loadData()

        binding = FragmentJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.jobsErgebnisseRV.setHasFixedSize(true)

        viewModel.jobs.observe(viewLifecycleOwner) {
            binding.jobsErgebnisseRV.adapter = JobsAdapter(it, viewModel)
        }

    }
}