package com.example.albatrossconnect.fragments.ProfessorFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.albatrossconnect.R
import com.example.albatrossconnect.databinding.FragmentCourseDetailBinding
import com.example.albatrossconnect.databinding.FragmentProfessorBinding
import com.example.albatrossconnect.databinding.FragmentProfessorDetailBinding

class ProfessorDetailFragment : Fragment() {
    private lateinit var binding: FragmentProfessorDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfessorDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        binding.professorReviews.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_professorDetailFragment_to_professorReviewFragment)
        }

        binding.courses.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_professorDetailFragment_to_courseFragment)
        }
    }
}