package com.example.uiexamples

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.uiexamples.model.*

class CreateGrupoForm : AppCompatActivity() {
    var codigoCurso = ""
    var cedProfe = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_grupo_form)

        var grupos: Grupos = Grupos.instance

        val numero = findViewById<EditText>(R.id.et_numero)
        val horario = findViewById<EditText>(R.id.et_horario)
        val save = findViewById<Button>(R.id.saveButton)

        var  cursos_spinner = findViewById(R.id.cursos_spinner) as Spinner
        cursos_spinner.adapter = ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,getCursosList())

        cursos_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                codigoCurso = getCursosList().get(position)
            }

        }

        var  profes_spinner = findViewById(R.id.profesores_spinner) as Spinner
        profes_spinner.adapter = ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,getProfesorList())

        profes_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                cedProfe = getProfesorList().get(position)
            }

        }


        save.setOnClickListener {
            var grupos: Grupos = Grupos.instance

            val intent = Intent(this, CrudGrupos::class.java)
            val grupo = Grupo(
                numero = numero.text.toString(),
                profesor = cedProfe,
                curso = codigoCurso,
                horario = horario.text.toString()
            )
            grupos.addGrupo(grupo)
            startActivity(intent)
            finish()
        }

    }

    fun getCursosList(): ArrayList<String>{
        var curosos: Cursos = Cursos.instance
        var list: ArrayList<String> = ArrayList()
        curosos.getCursos().forEach{
            list.add(it.codigo)
        }
        return list
    }

    fun getProfesorList(): ArrayList<String>{
        var profes: Profesores = Profesores.instance
        var list: ArrayList<String> = ArrayList()
        profes.getProfesores().forEach{
            list.add(it.cedula)
        }
        return list
    }
}