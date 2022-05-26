package com.example.uiexamples

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreatePersonForm : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_person_form)

        var personas: Personas = Personas.instance
        val name = findViewById<EditText>(R.id.textName)
        val user = findViewById<EditText>(R.id.textUsername)
        val password = findViewById<EditText>(R.id.textPassword)
        val save = findViewById<Button>(R.id.saveButton)
        val id = findViewById<EditText>(R.id.textID)
        val address = findViewById<EditText>(R.id.TextAddress)
        val email = findViewById<EditText>(R.id.TextEmailAddress)
        val phone = findViewById<EditText>(R.id.TextPhone)
        val profile = findViewById<AutoCompleteTextView>(R.id.TextProfile)

        cargarAutoCompleteTextView()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        save.setOnClickListener {
            val intent = Intent(this, CrudPersonas::class.java)
            val person = Persona(
                user = user.text.toString(),
                password = password.text.toString(),
                nombre = name.text.toString(),
                id = id.text.toString(),
                address = address.text.toString(),
                email = email.text.toString(),
                phone = phone.text.toString(),
                profile = profile.text.toString(),
                foto = R.drawable.foto02, startDate = current.format(formatter),
                position = "Mobile Developer"
            )
            personas.addPersona(person)
            startActivity(intent)
        }

    }

    private fun cargarAutoCompleteTextView() {
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.TextProfile)
        val profiles: Array<String> = arrayOf("admin", "viewer")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, profiles)
        autoCompleteTextView.threshold = 0
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnFocusChangeListener({ view, b -> if (b) autoCompleteTextView.showDropDown() })


    }

}