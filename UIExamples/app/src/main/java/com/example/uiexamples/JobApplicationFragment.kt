package com.example.uiexamples

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.uiexamples.model.Persona

class JobApplicationFragment : Fragment() {

    companion object {
        fun newInstance() = JobApplicationFragment()
    }

    private lateinit var viewModel: JobApplicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.job_application_fragment,container,false)
        var sentPerson = arguments?.getSerializable("Login")
        println(sentPerson.toString())
        if (sentPerson != null){
            println("persona no nula")
          var persona = sentPerson as Persona
          view.findViewById<TextView>(R.id.textID).setText(persona.id)

        }
        return inflater.inflate(R.layout.job_application_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JobApplicationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}