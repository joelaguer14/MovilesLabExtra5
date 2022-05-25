package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreatePersonForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_person_form)
        var personas: Personas = Personas.instance
        val name =  findViewById<EditText>(R.id.textName)
        val user =  findViewById<EditText>(R.id.textUsername)
        val password =  findViewById<EditText>(R.id.textPassword)
        val save =  findViewById<Button>(R.id.saveButton)
        val id =  findViewById<EditText>(R.id.textID)
        val address =  findViewById<EditText>(R.id.TextAddress)
        val email =  findViewById<EditText>(R.id.TextEmailAddress)
        val phone =  findViewById<EditText>(R.id.TextPhone)
        val profile =  findViewById<EditText>(R.id.TextProfile)


        save.setOnClickListener{
            val intent = Intent(this, CrudPersonas::class.java)
            val person = Persona(user = user.text.toString(), password = password.text.toString(), nombre = name.text.toString(),id=id.text.toString(),
                address=address.text.toString(),  email = email.text.toString(), phone = phone.text.toString(), profile=profile.text.toString(),foto =  R.drawable.foto02,"22","33")
            personas.addPersona(person)
            startActivity(intent)
        }
    }
}