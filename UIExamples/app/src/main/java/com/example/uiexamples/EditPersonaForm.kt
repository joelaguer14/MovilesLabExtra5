package com.example.uiexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText


class EditPersonaForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_persona_form)
        val bundle = intent.extras
        val msg = bundle!!.getString("msg")
        val l =  bundle.getSerializable("Persona") as Persona
        val name =  findViewById<EditText>(R.id.editTextPersonName)
        val user =  findViewById<EditText>(R.id.editTextPersonUser)
        val password =  findViewById<EditText>(R.id.editTextPersonPassword)

        name.setText(l.nombre)
        user.setText(l.user)
        password.setText(l.password)

    }
}