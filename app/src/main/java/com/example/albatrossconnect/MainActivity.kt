package com.example.albatrossconnect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albatrossconnect.fragments.CourseFlow.CourseFragment
import com.example.albatrossconnect.fragments.ProfessorFlow.ProfessorFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val courseFragment = CourseFragment()
        val professorFragment = ProfessorFragment()



        }

}