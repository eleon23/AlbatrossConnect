package com.example.albatrossconnect.fragments.CourseFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Review
import com.example.albatrossconnect.databinding.CourseReviewFragmentBinding
import com.example.albatrossconnect.databinding.CourseReviewItemBinding


class CourseReviewFragment : Fragment() {
    private lateinit var binding: CourseReviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CourseReviewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCourseTitle()
        setUpRecyclerView()
    }

    //TODO Fetch arguments to set this
    private fun setUpCourseTitle() {
        binding.courseTitle.text = "CS 141 | Intro to Computer Science"
    }

    private fun setUpRecyclerView() {
        binding.reviewsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            val adapter = ReviewAdapter(setUpMockData())
            setAdapter(adapter)
        }
    }

    private fun setUpMockData() : List<Review> {
        return listOf(
            Review(
                1,
                "Fall 2021",
                "Dr. Dale Reed",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

            )
        )
    }
}

class ReviewAdapter(private val data: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    class ViewHolder(val binding: CourseReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CourseReviewItemBinding
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
            instructorValue.text = studentReview.instructor
            review.text = studentReview.review

        }
    }

    override fun getItemCount(): Int = data!!.size
}