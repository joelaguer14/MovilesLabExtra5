package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.uiexamples.model.Curso
import com.example.uiexamples.model.Cursos

class EditCursoForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_curso_form)
        var cursos: Cursos = Cursos.instance
        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedCurso = bundle.getSerializable("Curso") as Curso

        val codigo = findViewById<EditText>(R.id.et_codigo)
        val nombre = findViewById<EditText>(R.id.et_nombre)
        val creditos = findViewById<EditText>(R.id.et_creditos)
        val horas = findViewById<EditText>(R.id.et_horas)

        val save = findViewById<Button>(R.id.saveButton)



        codigo.setText(recivedCurso.codigo)
        nombre.setText(recivedCurso.nombre)
        creditos.setText(recivedCurso.creditos)
        horas.setText(recivedCurso.horas)

        save.setOnClickListener {
            val intent = Intent(this, CrudCursos::class.java)
            recivedCurso.codigo = codigo.text.toString()
            recivedCurso.nombre = nombre.text.toString()
            recivedCurso.creditos = creditos.text.toString()
            recivedCurso.horas = horas.text.toString()
            cursos.editCurso(recivedCurso, position)
            startActivity(intent)
            finish()
        }
    }
}