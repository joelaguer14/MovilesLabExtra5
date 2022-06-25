package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas
import com.example.uiexamples.ui.Login


class EditPersonaForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_persona_form)
        var personas: Personas = Personas.instance
        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedPerson =  bundle.getSerializable("Persona") as Persona
        val user =  findViewById<EditText>(R.id.editTextPersonUser)
        val password =  findViewById<EditText>(R.id.editTextPersonPassword)
        val save =  findViewById<Button>(R.id.saveButton)
        val matriculador =  findViewById<CheckBox>(R.id.checkBox)
        val profile =  findViewById<AutoCompleteTextView>(R.id.TextProfile)
        cargarAutoCompleteTextView()


        user.setText(recivedPerson.user)
        password.setText(recivedPerson.password)
        matriculador.isChecked=recivedPerson.matriculador
        profile.setText(recivedPerson.profile)

        save.setOnClickListener{
            val intent = Intent(this, CrudPersonas::class.java)
            recivedPerson.user = user.text.toString()
            recivedPerson.password = password.text.toString()
            recivedPerson.matriculador=matriculador.isChecked
            recivedPerson.profile=profile.text.toString()

            personas.editPerson(recivedPerson,position)
            startActivity(intent)
            finish()
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