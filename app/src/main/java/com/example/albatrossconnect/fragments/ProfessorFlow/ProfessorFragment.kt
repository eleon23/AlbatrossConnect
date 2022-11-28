package com.example.albatrossconnect.fragments.ProfessorFlow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import com.example.albatrossconnect.ContactList
import com.example.albatrossconnect.ProfessorAndSchoolQuery
import com.example.albatrossconnect.ProfessorDetailsQuery
import com.example.albatrossconnect.R
import com.example.albatrossconnect.data.Professor
import com.example.albatrossconnect.data.Professors
import com.example.albatrossconnect.data.RawProfessors
import com.example.albatrossconnect.databinding.ProfessorCardCellBinding
import com.example.albatrossconnect.databinding.ProfessorFragmentBinding
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.*

class ProfessorFragment : Fragment() {
    private lateinit var binding: ProfessorFragmentBinding

    private lateinit var rawProfessors: RawProfessors
    private val mappedProfessors = mutableListOf<Professor>()

    private val data = MutableLiveData<List<Professor>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfessorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<List<Professor>> {
            binding.subTitle.text = getString(R.string.select_a_professor_you_want)
            setUpRecyclerView()
        }

        data.observe(viewLifecycleOwner, observer)
        binding.subTitle.text = "One second we are loading the professors"
        setUpProfessorData()
        setUpBottomNavBar()
        binding.fab.setOnClickListener {
            startActivity(Intent(context, ContactList::class.java))
        }
    }

    private fun setUpProfessorData() {
        val mapper = ObjectMapper()
        rawProfessors =
            mapper.readValue(context?.assets?.open("professor.json"), RawProfessors::class.java)
        println(rawProfessors)

        GlobalScope.launch {
            rawProfessors.professors.forEachIndexed { index, rawProfessor ->
            val rating = getProfessorRating(rawProfessor.name)
            println(rating)
            mappedProfessors.add(
                    Professor(
                        rawProfessor.name,
                        rawProfessor.position,
                        rawProfessor.department,
                        rating!!,
                        rawProfessor.phone,
                        rawProfessor.email,
                        rawProfessor.pic
                    )
                )
            }
            data.postValue(mappedProfessors)
        }
    }

    private suspend fun getProfessorRating(name: String): Double? {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://www.ratemyprofessors.com/graphql")
            .httpHeaders(listOf(HttpHeader("authorization", "Basic dGVzdDp0ZXN0")))
            .build()
        // UIC is U2Nob29sLTExMTE=
        //Access data like this schoolResponse.data.newSearch.teachers.edges[0].node
        val professorResponse = apolloClient.query(
                ProfessorAndSchoolQuery(
                    text = name,
                    schoolID = "U2Nob29sLTExMTE="
                )
            ).execute()


        val professorData = professorResponse.data?.newSearch?.teachers?.edges
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

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                 R.id.ic_courses-> {
                    view?.findNavController()?.navigate(R.id.action_professorFragment_to_courseFragment)
                     true
                }
                R.id.ic_professors -> {
                    true
                } else -> false
            }
        }
    }

    private fun setUpMockData() : List<Professor> {
        return listOf(
            Professor(
                "Dr. Dale Reed",
                "Research Professor",
                "Computer Science",
                5.0,
                "7731234789",
                "someEmail@Gmail.com",
                "SomePic"
            )
        )
    }

    private fun setUpRecyclerView() {
        binding.recyclerProfessor.apply {
            layoutManager = LinearLayoutManager(context)
//            val adapterData = arguments?.getSerializable("professors")
            val adapter = ProfessorAdapter(mappedProfessors)
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
            professorPhone.text = professor.phone
            professorEmail.text = professor.email
           professorRating.rating = professor.rating.toFloat()

            professorDescription.setOnClickListener {
                val data = bundleOf()
                data.putSerializable("professor", professor)
                root.findNavController().navigate(R.id.action_professorFragment_to_professorDetailFragment, data)
            }
        }
    }

    override fun getItemCount(): Int = data!!.size
}