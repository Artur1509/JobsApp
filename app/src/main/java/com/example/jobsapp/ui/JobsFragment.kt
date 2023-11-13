package com.example.jobsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.jobsapp.JobsViewModel
import com.example.jobsapp.R
import com.example.jobsapp.databinding.FragmentJobsBinding
import com.example.jobsapp.util.JobsAdapter

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private lateinit var jobSearch: SearchView
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

        val navBar = requireActivity().findViewById<View>(R.id.bottomNavigation)
        navBar.visibility = View.VISIBLE

        jobSearch = binding.searchSV

        binding.jobsErgebnisseRV.setHasFixedSize(true)

        // Adapter wird erstellt und initialisiert
        viewModel.jobs.observe(viewLifecycleOwner) {
            binding.jobsErgebnisseRV.adapter = JobsAdapter(it, viewModel)
        }

        // Suchfunktion nach Berufsfeld
        jobSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (!query.isNullOrBlank()) {
                    viewModel.loadJobsByJobField(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (!newText.isNullOrBlank()) {
                    viewModel.loadJobsByJobField(newText)
                }

                return true
            }
        })
    }

}
