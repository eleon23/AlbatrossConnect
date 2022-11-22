package com.example.albatrossconnect.fragments.ProfessorFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Professor
import com.example.albatrossconnect.databinding.FragmentProfessorBinding
import com.example.albatrossconnect.databinding.PrerequisiteItemBinding
import com.example.albatrossconnect.databinding.ProfessorCardCellBinding
import com.example.albatrossconnect.fragments.CourseFlow.CourseAdapter

class ProfessorFragment : Fragment() {
    private lateinit var binding: FragmentProfessorBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfessorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

    }

    private fun setUpMockData() : List<Professor> {
        return listOf(
            Professor(
                "Dr. Dale Reed",
                "Research Professor",
                "Computer Science",
                5,
                7731234789,
                "someEmail@Gmail.com"
            )
        )
    }

    private fun setUpRecyclerView() {
        binding.recyclerProfessor.apply {
            layoutManager = LinearLayoutManager(context)
            val adapter = ProfessorAdapter(setUpMockData())
            setAdapter(adapter)
        }
    }
}

class ProfessorAdapter(private val data: List<Professor>) :
    RecyclerView.Adapter<ProfessorAdapter.ViewHolder>() {
    class ViewHolder(val binding: ProfessorCardCellBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProfessorCardCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val professor = data[position]
        holder.binding.apply {
            professorName.text = professor.name
            professorPosition.text = professor.position
            department.text = professor.department
            professorPhone.text = professor.phone.toString()
            professorEmail.text = professor.email
            professorRating.text = professor.rating.toString()

            professorDescription.setOnClickListener {
                root.findNavController().navigate(R.id.action_professorFragment_to_professorDetailFragment)
            }
        }
    }

    override fun getItemCount(): Int = data!!.size
}