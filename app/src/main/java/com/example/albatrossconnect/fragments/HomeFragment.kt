package com.example.albatrossconnect.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import com.example.albatrossconnect.ProfessorAndSchoolQuery
import com.example.albatrossconnect.ProfessorDetailsQuery
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Professor
import com.example.albatrossconnect.data.Professors
import com.example.albatrossconnect.data.RawProfessor
import com.example.albatrossconnect.data.RawProfessors
import com.example.albatrossconnect.databinding.HomeFragmentBinding
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.nio.channels.FileChannel.open

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        binding.courseDescriptions.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_courseFragment)
        }

        binding.professorLookup.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_homeFragment_to_professorFragment)
        }
    }
}