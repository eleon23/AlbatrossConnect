package com.example.albatrossconnect.data


data class CourseDetails(
    val courses: List<Course>
)

data class Course(
    val courseName: String,
    val courseNumber: Int,
    val professor: String,
    val rating: Int,
    val CRN: Int
)