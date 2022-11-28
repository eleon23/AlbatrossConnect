package com.example.albatrossconnect.fragments.CourseFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Course
import com.example.albatrossconnect.databinding.CourseDetailFragmentBinding

class CourseDetailFragment : Fragment() {
    private lateinit var binding: CourseDetailFragmentBinding
    private lateinit var course: Course
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CourseDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCourseDetails()
        setUpOnClickListeners()
        setUpBottomNavBar()
    }

    private fun setUpCourseDetails() {
        course = arguments?.getSerializable("course") as Course

        binding.courseNumber.text = course.courseNumber
        binding.courseName.text = course.courseName

    }

    private fun setUpOnClickListeners() {
        val data = bundleOf()
        data.putSerializable("course", course)
        binding.studentReviews.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_courseDetailFragment_to_courseReviewFragment, data)
        }
        binding.prerequisites.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_courseDetailFragment_to_coursePrerequisiteFragment)
        }
    }

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_courses-> {
                    view?.findNavController()?.navigate(R.id.action_courseDetailFragment_to_courseFragment)
                    true
                }
                R.id.ic_professors -> {
                    view?.findNavController()?.navigate(R.id.action_courseDetailFragment_to_professorFragment)
                    true
                } else -> false
            }
        }
    }

}