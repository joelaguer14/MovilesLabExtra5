package com.example.uiexamples

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreatePersonForm : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_person_form)

        var personas: Personas = Personas.instance

        val user = findViewById<EditText>(R.id.textUsername)
        val password = findViewById<EditText>(R.id.textPassword)
        val save = findViewById<Button>(R.id.saveButton)
        val id = findViewById<EditText>(R.id.textID)
        val matriculador= findViewById<CheckBox>(R.id.checkBox)

        val profile = findViewById<AutoCompleteTextView>(R.id.TextProfile)

        cargarAutoCompleteTextView()


        save.setOnClickListener {
            val intent = Intent(this, CrudPersonas::class.java)
            val person = Persona(
                user = user.text.toString(),
                password = password.text.toString(),
                matriculador = matriculador.isChecked,
                id = id.text.toString(),
                profile = profile.text.toString()

            )
            personas.addPersona(person)
            startActivity(intent)
        }

    }

    private fun cargarAutoCompleteTextView() {
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.TextProfile)
        val profiles: Array<String> = arrayOf("Profesor", "Administrador", "Alumno")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, profiles)
        autoCompleteTextView.threshold = 0
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnFocusChangeListener({ view, b -> if (b) autoCompleteTextView.showDropDown() })


    }

}