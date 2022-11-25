package com.example.albatrossconnect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import com.example.albatrossconnect.databinding.ActivityMainBinding
import com.example.albatrossconnect.fragments.CourseFlow.CourseFragment
import com.example.albatrossconnect.fragments.HomeFragment
import com.example.albatrossconnect.fragments.ProfessorFlow.ProfessorFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        GlobalScope.launch{
            getSpecificProfessor()
        }

    }

    suspend fun getSpecificProfessor() {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://www.ratemyprofessors.com/graphql")
            .httpHeaders(listOf(HttpHeader("authorization", "Basic dGVzdDp0ZXN0")))
            .build()



        // UIC is U2Nob29sLTExMTE=
        //Access data like this schoolResponse.data.newSearch.teachers.edges[0].node
        val professorResponse = apolloClient.query(ProfessorAndSchoolQuery(text = "Dale Reed", schoolID = "U2Nob29sLTExMTE=")).execute()
        val professorId: String? =  professorResponse.data?.newSearch?.teachers?.edges?.get(0)?.node?.id
        val professorDetails = apolloClient.query(ProfessorDetailsQuery(
            id = professorId!!)).execute()

        println("Professor.id=${professorId}")
        println("Professor.details=${professorDetails.data}")
    }
}