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
import com.example.uiexamples.model.Curso
import com.example.uiexamples.model.Cursos
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateCursoForm : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_curso_form)

        var cursos: Cursos = Cursos.instance
        val name = findViewById<EditText>(R.id.textName)
        val code = findViewById<EditText>(R.id.textCodigoCurso)
        val creditos = findViewById<EditText>(R.id.textCredits)
        val save = findViewById<Button>(R.id.saveButton)
        val hours = findViewById<EditText>(R.id.textHoras)

        val profile = findViewById<AutoCompleteTextView>(R.id.TextProfile)

//        cargarAutoCompleteTextView()


        save.setOnClickListener {
            val intent = Intent(this, CrudCursos::class.java)
            val curso = Curso(
                codigo = code.text.toString(),
                creditos = creditos.text.toString(),
                nombre = name.text.toString(),
                horas = hours.text.toString()
            )
            cursos.addCurso(curso)
            startActivity(intent)
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