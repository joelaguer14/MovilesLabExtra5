package com.example.uiexamples

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uiexamples.model.*

class CreateAlumnoForm : AppCompatActivity() {
    var codigoCarrera = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alumno_form)

        var alumnos: Alumnos = Alumnos.instance
        val name = findViewById<EditText>(R.id.et_nombre)
        val cedula = findViewById<EditText>(R.id.et_cedula)
        val telefono = findViewById<EditText>(R.id.et_telefono)
        val email = findViewById<EditText>(R.id.et_email)
        val fechaNac = findViewById<EditText>(R.id.et_fechaNac)

        val save = findViewById<Button>(R.id.saveButton)

        var  carreras_spinner = findViewById(R.id.carreras_spinner) as Spinner
        carreras_spinner.adapter = ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,getCarrerList())

        carreras_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                codigoCarrera = getCarrerList().get(position)
            }

        }


        save.setOnClickListener {
            var carreras: Carreras = Carreras.instance
            var personas: Personas = Personas.instance

            val carrera = carreras.getCarrera(codigoCarrera)
            val intent = Intent(this, CrudAlumnos::class.java)
            val alumno = Alumno(
                cedula = cedula.text.toString(),
                nombre = name.text.toString(),
                telefono = telefono.text.toString(),
                email = email.text.toString(),
                fecNac = fechaNac.text.toString(),
                carrera = carrera
            )
            val persona = Persona(
                user = name.text.toString(),
                password = "123",
                matriculador = false,
                id = cedula.text.toString(),
                profile = "Alumno"
            )
            personas.addPersona(persona)
            alumnos.addAlumno(alumno)
            startActivity(intent)
            finish()
        }

    }

    fun getCarrerList(): ArrayList<String>{
        var carreras: Carreras = Carreras.instance
        var list: ArrayList<String> = ArrayList()
        carreras.getCarreras().forEach{
            list.add(it.codigo)
        }
        return list
    }
}