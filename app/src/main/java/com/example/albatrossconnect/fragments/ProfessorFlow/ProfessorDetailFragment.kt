package com.example.albatrossconnect.fragments.ProfessorFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Professor
import com.example.albatrossconnect.databinding.ProfessorDetailFragmentBinding

class ProfessorDetailFragment : Fragment() {
    private lateinit var binding: ProfessorDetailFragmentBinding
    private lateinit var professor: Professor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfessorDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        setUpBottomNavBar()
        getProfessorData()
    }

    private fun getProfessorData() {
        professor = arguments?.getSerializable("professor") as Professor
        binding.apply {
            professorName.text = professor.name
            professorPosition.text = professor.position
            deparment.text = professor.department
            phoneInfo.text = professor.phone
            emailInfo.text = professor.email
            officeInfo.text = "UIC Office Placeholder"
        }
    }

    private fun setUpOnClickListeners() {
        binding.professorReviews.setOnClickListener {
            val data = bundleOf()
            data.putString("professorName", professor.name)
            binding.root.findNavController().navigate(R.id.action_professorDetailFragment_to_professorReviewFragment, data)
        }

        binding.courses.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_professorDetailFragment_to_courseFragment)
        }
    }
    private fun setUpBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_courses-> {
                    view?.findNavController()?.navigate(R.id.action_professorDetailFragment_to_courseFragment)
                    true
                }
                R.id.ic_professors -> {
                    view?.findNavController()?.navigate(R.id.action_professorDetailFragment_to_professorFragment)
                    true
                } else -> false
            }
        }
    }
}