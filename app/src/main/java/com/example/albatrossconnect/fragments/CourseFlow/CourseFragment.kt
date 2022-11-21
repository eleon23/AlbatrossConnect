package com.example.albatrossconnect.fragments.CourseFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Course
import com.example.albatrossconnect.databinding.FragmentCourseBinding
import com.example.albatrossconnect.databinding.FragmentHomeBinding
import com.example.albatrossconnect.databinding.PrerequisiteItemBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseFragment : Fragment() {
    private lateinit var binding: FragmentCourseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

    }

    private fun setUpMockData() : List<Course> {
        return listOf(
            Course(
                "Introduction to CS",
                141,
                "Dr. Dale Reed",
                4,
                123456
            )
        )
    }

    private fun setUpRecyclerView() {
        binding.recyclerCourses.apply {
            layoutManager = LinearLayoutManager(context)
            val adapter = CourseAdapter(setUpMockData())
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
            courseNumber.text = "CS ${course.courseNumber}"
            courseTitle.text = course.courseName
            professorName.text = course.professor
            courseRating.text = course.rating.toString()
            CRN.text = "CRN: ${course.CRN}"

            courseDescription.setOnClickListener {
                root.findNavController().navigate(R.id.action_courseFragment_to_courseDetailFragment)
            }
        }
    }

    override fun getItemCount(): Int = data!!.size
}