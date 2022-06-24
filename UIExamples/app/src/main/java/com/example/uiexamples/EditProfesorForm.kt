package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.uiexamples.model.Profesor
import com.example.uiexamples.model.Profesores

class EditProfesorForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profesor_form)
        var profesores: Profesores = Profesores.instance
        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedProfesor = bundle.getSerializable("Profesor") as Profesor

        val cedula = findViewById<EditText>(R.id.textCedulaProfesor)
        val nombre = findViewById<EditText>(R.id.textNombreProfesor)
        val telefono = findViewById<EditText>(R.id.textTelefonoProfesor)
        val email = findViewById<EditText>(R.id.textEmailProfesor)

        val save = findViewById<Button>(R.id.saveButton)



        cedula.setText(recivedProfesor.cedula)
        nombre.setText(recivedProfesor.nombre)
        telefono.setText(recivedProfesor.telefono)
        email.setText(recivedProfesor.email)

        save.setOnClickListener {
            val intent = Intent(this, CrudProfesores::class.java)
            recivedProfesor.cedula = cedula.text.toString()
            recivedProfesor.nombre = nombre.text.toString()
            recivedProfesor.telefono = telefono.text.toString()
            recivedProfesor.email = email.text.toString()
            profesores.editProfesor(recivedProfesor, position)
            startActivity(intent)
            finish()
        }
    }
}