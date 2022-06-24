package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.uiexamples.model.Carrera
import com.example.uiexamples.model.Carreras
import com.example.uiexamples.model.Curso
import com.example.uiexamples.model.Cursos

class EditCarreraForm : AppCompatActivity() {
    var codigoCurso = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_carrera_form)
        var carreras: Carreras = Carreras.instance

        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedCarrera = bundle.getSerializable("Carrera") as Carrera

        var  cursos_spinner = findViewById(R.id.cursos_spinner) as Spinner
        cursos_spinner.adapter = ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,getCourseList())

        cursos_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                codigoCurso = getCourseList().get(position)
            }

        }
        val codigo = findViewById<EditText>(R.id.et_codigo)
        val nombre = findViewById<EditText>(R.id.et_nombre)
        val titulo = findViewById<EditText>(R.id.et_titulo)


        val save = findViewById<Button>(R.id.saveButton)
        val add_curso = findViewById<Button>(R.id.btn_addcurso)



        codigo.setText(recivedCarrera.codigo)
        nombre.setText(recivedCarrera.nombre)
        titulo.setText(recivedCarrera.titulo)


        save.setOnClickListener {
            val intent = Intent(this, CrudCarreras::class.java)
            recivedCarrera.codigo = codigo.text.toString()
            recivedCarrera.nombre = nombre.text.toString()
            recivedCarrera.titulo = titulo.text.toString()

            carreras.editCarrera(recivedCarrera, position)
            startActivity(intent)
            finish()
        }

        add_curso.setOnClickListener{
            var cursos: Cursos = Cursos.instance
            cursos.getCurso(codigoCurso)?.let { it1 -> carreras.addCourse(it1, position) }
            cursos_spinner.setSelection(0)
        }


    }
    fun getCourseList(): ArrayList<String>{
        var cursos: Cursos = Cursos.instance
        var list: ArrayList<String> = ArrayList()
        cursos.getCursos().forEach{
            list.add(it.codigo)
        }

        return list
    }
}