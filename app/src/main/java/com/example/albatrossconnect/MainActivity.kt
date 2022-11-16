package com.example.albatrossconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import com.example.albatrossconnect.fragments.CourseFragment
import com.example.albatrossconnect.fragments.ProfessorFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val courseFragment = CourseFragment()
        val professorFragment = ProfessorFragment()



        }

    }

}