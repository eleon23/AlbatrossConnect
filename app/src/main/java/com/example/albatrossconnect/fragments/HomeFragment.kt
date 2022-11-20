package com.example.albatrossconnect.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.albatrossconnect.R
import com.example.albatrossconnect.databinding.ActivityMainBinding
import com.example.albatrossconnect.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners(){
        binding.courseDescriptions.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_courseFragment)
        }

        binding.professorLookup.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_professorFragment)
        }
    }
}