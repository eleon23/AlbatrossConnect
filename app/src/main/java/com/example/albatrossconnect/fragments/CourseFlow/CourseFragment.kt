package com.example.albatrossconnect.fragments.CourseFlow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albatrossconnect.ContactList
import com.example.albatrossconnect.MainActivity
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Course
import com.example.albatrossconnect.data.CourseDetails
import com.example.albatrossconnect.data.RawProfessors
import com.example.albatrossconnect.databinding.CourseFragmentBinding
import com.example.albatrossconnect.databinding.PrerequisiteItemBinding
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * A simple [Fragment] subclass.
 * Use the [CourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseFragment : Fragment() {
    private lateinit var binding: CourseFragmentBinding
    private lateinit var courses: CourseDetails

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CourseFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBottomNavBar()
        setUpCoursesData()
        setUpRecyclerView()
        binding.fab.setOnClickListener {
            startActivity(Intent(context, ContactList::class.java))
        }
    }

    private fun setUpCoursesData() {
        val mapper = ObjectMapper()
        courses =
            mapper.readValue(context?.assets?.open("courses.json"), CourseDetails::class.java)
        println(courses)
    }

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_courses-> {
                    true
                }
                R.id.ic_professors -> {
                    view?.findNavController()?.navigate(R.id.action_courseFragment_to_professorFragment)
                    true
                } else -> false
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerCourses.apply {
            layoutManager = LinearLayoutManager(context)
            val adapter = CourseAdapter(courses.courses)
            setAdapter(adapter)
        }
    }
}

class CourseAdapter(private val data: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
        class ViewHolder(val binding: PrerequisiteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PrerequisiteItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = data[position]
        holder.binding.apply {
            courseNumber.text = course.courseNumber
            courseTitle.text = course.courseName
            professorName.text = course.professor
            prerequisiteCourseRating.rating = course.rating
            CRN.text = "CRN: ${course.CRN}"

            courseDescription.setOnClickListener {
                val data = bundleOf()
                data.putSerializable("course", course)
                root.findNavController().navigate(R.id.action_courseFragment_to_courseDetailFragment, data)
            }
        }
    }

    override fun getItemCount(): Int = data!!.size
}