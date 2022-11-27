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
    private lateinit var rawProfessors: RawProfessors
    private val mappedProfessors = mutableListOf<Professor>()
    private lateinit var professors: Professors
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
        setUpProfessorData()
    }

    private fun setUpProfessorData() {
        val mapper = ObjectMapper()
        rawProfessors =
            mapper.readValue(context?.assets?.open("professor.json"), RawProfessors::class.java)
        println(rawProfessors)

        rawProfessors.professors.forEachIndexed { index, rawProfessor ->
            GlobalScope.launch {
                val rating = getProfessorRating(rawProfessor.name)
                println(rating)
                mappedProfessors.add(
                    Professor(
                        rawProfessor.name,
                        rawProfessor.position,
                        rawProfessor.department,
                        rating!!,
                        rawProfessor.phone,
                        rawProfessor.email
                    )
                )
            }
        }
    }

    private suspend fun getProfessorRating(name: String): Double? {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://www.ratemyprofessors.com/graphql")
            .httpHeaders(listOf(HttpHeader("authorization", "Basic dGVzdDp0ZXN0")))
            .build()
        // UIC is U2Nob29sLTExMTE=
        //Access data like this schoolResponse.data.newSearch.teachers.edges[0].node
        val professorResponse =  GlobalScope.async {
            apolloClient.query(
                ProfessorAndSchoolQuery(
                    text = name,
                    schoolID = "U2Nob29sLTExMTE="
                )
            ).execute()
        }

        val professorData = professorResponse.await().data?.newSearch?.teachers?.edges
        if (professorData?.size == 0) {
            return 0.0
        }
        val professorId: String? =
            professorData?.get(0)?.node?.id

        val professorDetails = apolloClient.query(
            ProfessorDetailsQuery(
                id = professorId!!
            )
        ).execute()

        println("Professor.id=${professorId}")
        println("Professor.details=${professorDetails.data}")

        return professorDetails.data?.node?.onTeacher?.avgRating
    }

    private fun setUpOnClickListeners() {
        binding.courseDescriptions.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_courseFragment)
        }

        binding.professorLookup.setOnClickListener {
            val bundle = bundleOf()
            professors = Professors(mappedProfessors)
            bundle.putSerializable("professors", professors)
            view?.findNavController()
                ?.navigate(R.id.action_homeFragment_to_professorFragment, bundle)
        }
    }
}