package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas

class JobAppEditForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_app_edit_form)
        var personas: Personas = Personas.instance
        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedPerson =  bundle.getSerializable("Persona") as Persona
        val name =  findViewById<EditText>(R.id.editTextPersonName)
        val id =  findViewById<EditText>(R.id.editTextPersonId)
        val save =  findViewById<Button>(R.id.saveButton)



        name.setText(recivedPerson.nombre)
        id.setText(recivedPerson.id)



        save.setOnClickListener{

            recivedPerson.nombre = name.text.toString()
            recivedPerson.id = id.text.toString()

            personas.editPerson(recivedPerson,position)

            val i = Intent(this, CrudPersonas::class.java)
            finish()
            startActivity(i)
        }
    }
}