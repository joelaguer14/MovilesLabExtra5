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

        save.setOnClickListener{
            val intent = Intent(this, CrudPersonas::class.java)
            val person = Persona(user = user.text.toString(), password = password.text.toString(), nombre = name.text.toString(),foto =  R.drawable.foto01)
            personas.addPersona(person)
            startActivity(intent)
        }
    }
}