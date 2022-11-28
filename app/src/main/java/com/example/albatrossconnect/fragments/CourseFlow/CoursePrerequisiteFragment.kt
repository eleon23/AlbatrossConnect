package com.example.albatrossconnect.fragments.CourseFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Course
import com.example.albatrossconnect.data.Prerequisite
import com.example.albatrossconnect.databinding.CoursePrerequisiteFragmentBinding
import com.example.albatrossconnect.databinding.PrerequisiteItemBinding

class CoursePrerequisiteFragment : Fragment() {

    private lateinit var binding: CoursePrerequisiteFragmentBinding
    private lateinit var prerequisites: List<Prerequisite>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CoursePrerequisiteFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setUpRecyclerView()
        setUpBottomNavBar()

    }

    private fun getData() {
      //  prerequisites = arguments?.getSerializable("course") as List<Prerequisite>
    }

    private val mockUpData = listOf<Course>(
        Course(
            "CS111",
            "3",
            "Intro to Computer Science",
            "This class is an introduction to computer science",
            "schedule",
            emptyList(),
            "1234567",
            "Dale Reed"
        ),
        Course(
            "CS141",
            "3",
            "Intro to Computer Science Part 2",
            "This class is an introduction to computer science Part 2",
            "schedule",
            emptyList(),
            "7654321",
            "Dale Reed"
        ),
    )

    private fun setUpRecyclerView() {
        binding.recyclerPrerequisites.apply {
            layoutManager = LinearLayoutManager(context)
            val adapter = PrerequisiteAdapter(mockUpData)
            setAdapter(adapter)
        }
    }

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_courses-> {
                    view?.findNavController()?.navigate(R.id.action_coursePrerequisiteFragment_to_courseFragment)
                    true
                }
                R.id.ic_professors -> {
                    view?.findNavController()?.navigate(R.id.action_coursePrerequisiteFragment_to_professorFragment)
                    true
                } else -> false
            }
        }
    }
}



class PrerequisiteAdapter(private val data: List<Course>) :
    RecyclerView.Adapter<PrerequisiteAdapter.ViewHolder>() {
    class ViewHolder(val binding: PrerequisiteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PrerequisiteItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = data[position]
        holder.binding.apply {
            courseNumber.text = "CS ${course.courseNumber}"
            courseTitle.text = course.courseName
            professorName.text = course.professor
            //courseRating.text = course.rating.toString()
            CRN.text = "CRN: ${course.CRN}"

            courseDescription.setOnClickListener {
                root.findNavController().navigate(R.id.action_coursePrerequisiteFragment_to_courseDetailFragment)
            }
        }
    }

    override fun getItemCount(): Int = data!!.size
}