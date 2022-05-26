package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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
        val addres =  findViewById<EditText>(R.id.editTextPersonAddres)
        val pos =  findViewById<EditText>(R.id.editTextPersonPosition)
        val startDate =  findViewById<EditText>(R.id.editTextPersonStartDate)
        val email =  findViewById<EditText>(R.id.editTextPersonEmail)
        val phone =  findViewById<EditText>(R.id.editTextPersonPhone)
        val save =  findViewById<Button>(R.id.saveButton)



        name.setText(recivedPerson.nombre)
        id.setText(recivedPerson.id)
        addres.setText(recivedPerson.address)
        pos.setText(recivedPerson.position)
        startDate.setText(recivedPerson.startDate)
        email.setText(recivedPerson.email)
        phone.setText(recivedPerson.phone)


        save.setOnClickListener{

            recivedPerson.nombre = name.text.toString()
            recivedPerson.id = id.text.toString()
            recivedPerson.address = addres.text.toString()
            recivedPerson.position = pos.text.toString()
            recivedPerson.startDate = startDate.text.toString()
            recivedPerson.email = email.text.toString()
            recivedPerson.phone = phone.text.toString()
            personas.editPerson(recivedPerson,position)

            val i = Intent(this, CrudPersonas::class.java)
            finish()
            startActivity(i)
        }
    }
}