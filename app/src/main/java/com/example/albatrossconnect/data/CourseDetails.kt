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

data class CourseReviews(
    val reviews: List<Review>
)

data class Review(
    val rating: Int,
    val semester: String,
    val instructor: String,
    val review: String
)

data class Professors(
    val professors: List<Professor>
)

data class Professor(
    val name: String,
    val position: String,
    val department: String,
    val rating: Int,
    val phone: Long,
    val email: String
)