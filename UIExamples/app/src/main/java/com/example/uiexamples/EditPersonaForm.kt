package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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



        user.setText(recivedPerson.user)
        password.setText(recivedPerson.password)

        save.setOnClickListener{
            val intent = Intent(this, LoginExample::class.java)
            recivedPerson.user = user.text.toString()
            recivedPerson.password = password.text.toString()
            personas.editPerson(recivedPerson,position)
            startActivity(intent)
            finish()
        }

    }
}