package com.example.albatrossconnect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albatrossconnect.databinding.ActivityMainBinding
import com.example.albatrossconnect.fragments.CourseFlow.CourseFragment
import com.example.albatrossconnect.fragments.HomeFragment
import com.example.albatrossconnect.fragments.ProfessorFlow.ProfessorFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}