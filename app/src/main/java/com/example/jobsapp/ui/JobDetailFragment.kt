package com.example.jobsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jobsapp.JobsViewModel
import com.example.jobsapp.R
import com.example.jobsapp.databinding.FragmentJobDetailBinding
import com.example.jobsapp.databinding.FragmentJobsBinding

class JobDetailFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailBinding
    private val viewModel: JobsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navBar = requireActivity().findViewById<View>(R.id.bottomNavigation)
        navBar.visibility = View.GONE

        viewModel.jobDetail.observe(viewLifecycleOwner) {
            binding.detailArbeitgeberTV.text = it.arbeitgeber
            binding.detailBerufTV.text = it.beruf
            binding.detailBrancheTV.text = it.branche
            binding.detailBeschreibungTV.text = it.stellenbeschreibung
            binding.detailTitelTV.text = it.titel
            binding.detailPartnerUrlTV.text = it.allianzpartnerUrl
        }

        binding.backToJobsBTN.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}