package com.example.uiexamples

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uiexamples.model.*

class CreateProfesorForm : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profesor_form)

        var profesores: Profesores = Profesores.instance
        val name = findViewById<EditText>(R.id.textNombreProfesor)
        val cedula = findViewById<EditText>(R.id.textCedulaProfesor)
        val telefono = findViewById<EditText>(R.id.textTelefonoProfesor)
        val save = findViewById<Button>(R.id.saveButton)
        val email = findViewById<EditText>(R.id.textEmailProfesor)




        save.setOnClickListener {
            val intent = Intent(this, CrudProfesores::class.java)
            var personas: Personas = Personas.instance
            val profesor = Profesor(
                cedula = cedula.text.toString(),
                telefono = telefono.text.toString(),
                nombre = name.text.toString(),
                email = email.text.toString()
            )
            val persona = Persona(
                user = name.text.toString(),
                password = "123",
                matriculador = false,
                id = cedula.text.toString(),
                profile = "Profesor"
            )
            profesores.addProfesor(profesor)
            personas.addPersona(persona)
            startActivity(intent)
            finish()
        }

    }
//
//    private fun cargarAutoCompleteTextView() {
//        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.TextProfile)
//        val profiles: Array<String> = arrayOf("admin", "viewer")
//        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, profiles)
//        autoCompleteTextView.threshold = 0
//        autoCompleteTextView.setAdapter(adapter)
//        autoCompleteTextView.setOnFocusChangeListener({ view, b -> if (b) autoCompleteTextView.showDropDown() })
//
//
//    }

}