package com.example.albatrossconnect.fragments.ProfessorFlow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albatrossconnect.ContactList
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Review
import com.example.albatrossconnect.databinding.ProfessorReviewFragmentBinding
import com.example.albatrossconnect.databinding.ProfessorReviewItemBinding

class ProfessorReviewFragment : Fragment() {
    private lateinit var binding: ProfessorReviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfessorReviewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCourseTitle()
        setUpRecyclerView()
        setUpBottomNavBar()
        binding.fab.setOnClickListener {
            startActivity(Intent(context, ContactList::class.java))
        }
    }

    //TODO Fetch arguments to set this
    private fun setUpCourseTitle() {
        val name = arguments?.getString("professorName")
        binding.professorName.text = name
    }

    private fun setUpRecyclerView() {
        binding.reviewsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            val adapter = ProfessorReviewAdapter(setUpMockData())
            setAdapter(adapter)
        }
    }

    private fun setUpMockData() : List<Review> {
        return listOf(
            Review(
                1,
                "Fall 2021",
                "Dr. Dale Reed",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "Intro to Computer Science"
            )
        )
    }

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_courses-> {
                    view?.findNavController()?.navigate(R.id.action_professorReviewFragment_to_courseFragment)
                    true
                }
                R.id.ic_professors -> {
                    view?.findNavController()?.navigate(R.id.action_professorReviewFragment_to_professorFragment)
                    true
                } else -> false
            }
        }
    }
}

class ProfessorReviewAdapter(private val data: List<Review>) :
    RecyclerView.Adapter<ProfessorReviewAdapter.ViewHolder>() {
    class ViewHolder(val binding: ProfessorReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProfessorReviewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentReview = data[position]
        holder.binding.apply {
            rating.text = studentReview.rating.toString()

            ratingSection.apply {
                when (studentReview.rating) {
                    in 4..5 -> {
                        setBackgroundColor(context.getColor(R.color.green))
                        ratingType.text = "Great"
                    }
                    in 2..3 -> {
                        setBackgroundColor(context.getColor(R.color.yellow))
                        ratingType.text = "Good"
                    }
                    else -> {
                        setBackgroundColor(context.getColor(R.color.red))
                        ratingType.text = "Bad"
                    }
                }
            }

            takenValue.text = studentReview.semester
            courseValue.text = studentReview.course
            review.text = studentReview.review

        }
    }

    override fun getItemCount(): Int = data!!.size
}