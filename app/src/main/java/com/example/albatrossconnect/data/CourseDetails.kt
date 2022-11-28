package com.example.albatrossconnect.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize


data class CourseDetails(
    @JsonProperty("courses")
    val courses: List<Course>
)

data class Course(
    @JsonProperty("number")
    val courseNumber: String,
    @JsonProperty("hours")
    val hours: String,
    @JsonProperty("meta")
    val courseName: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("schedule")
    val schedule: String,
    @JsonProperty("prerequisites")
    val prerequisites: List<Prerequisite>,
    @JsonProperty("CRN")
    val CRN: String,
    @JsonProperty("professor")
    val professor: String

): java.io.Serializable

data class Prerequisite(
    @JsonProperty("prerequisite")
    val prerequisite: String
): java.io.Serializable

data class CourseReviews(
    val reviews: List<Review>
): java.io.Serializable

data class Review(
    val rating: Int,
    val semester: String,
    val instructor: String,
    val review: String,
    val course: String? = null
)

data class Professors(
    var professors: List<Professor>
) : java.io.Serializable

data class Professor(
    val name: String,
    val position: String,
    val department: String,
    val rating: Double,
    val phone: String,
    val email: String
) : java.io.Serializable

data class RawProfessors(
    @JsonProperty("professors")
    val professors: List<RawProfessor>
) : java.io.Serializable

data class RawProfessor(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("position")
    val position: String,
    @JsonProperty("department")
    val department: String,
    @JsonProperty("phone")
    val phone: String,
    @JsonProperty("email")
    val email: String
): java.io.Serializable