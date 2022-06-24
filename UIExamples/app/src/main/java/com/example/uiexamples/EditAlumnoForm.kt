package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.uiexamples.model.Alumno
import com.example.uiexamples.model.Alumnos
import com.example.uiexamples.model.Profesor
import com.example.uiexamples.model.Profesores

class EditAlumnoForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alumno_form)
        var alumnos: Alumnos = Alumnos.instance
        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedAlumno = bundle.getSerializable("Alumno") as Alumno

        val cedula = findViewById<EditText>(R.id.et_cedula)
        val nombre = findViewById<EditText>(R.id.et_nombre)
        val telefono = findViewById<EditText>(R.id.et_telefono)
        val email = findViewById<EditText>(R.id.et_email)
        val fechaNac = findViewById<EditText>(R.id.et_fecNac)

        val save = findViewById<Button>(R.id.saveButton)



        cedula.setText(recivedAlumno.cedula)
        nombre.setText(recivedAlumno.nombre)
        telefono.setText(recivedAlumno.telefono)
        email.setText(recivedAlumno.email)
        fechaNac.setText(recivedAlumno.fecNac)


        save.setOnClickListener {
            val intent = Intent(this, CrudAlumnos::class.java)
            recivedAlumno.cedula = cedula.text.toString()
            recivedAlumno.nombre = nombre.text.toString()
            recivedAlumno.telefono = telefono.text.toString()
            recivedAlumno.email = email.text.toString()
            recivedAlumno.fecNac = fechaNac.text.toString()

            alumnos.editAlumno(recivedAlumno, position)
            startActivity(intent)
            finish()
        }
    }
}