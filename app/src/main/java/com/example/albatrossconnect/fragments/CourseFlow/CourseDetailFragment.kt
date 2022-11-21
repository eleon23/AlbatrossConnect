package com.example.albatrossconnect.fragments.CourseFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.albatrossconnect.R
import com.example.albatrossconnect.databinding.FragmentCourseBinding
import com.example.albatrossconnect.databinding.FragmentCourseDetailBinding

class CourseDetailFragment : Fragment() {
    private lateinit var binding: FragmentCourseDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        binding.studentReviews.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_courseDetailFragment_to_courseReviewFragment)
        }
        binding.prerequisites.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_courseDetailFragment_to_coursePrerequisiteFragment)
        }
    }

}