package com.example.uiexamples

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.uiexamples.model.Carrera
import com.example.uiexamples.model.Carreras


class CreateCarreraForm : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_carrera_form)

        var carreras: Carreras = Carreras.instance

        val codigo = findViewById<EditText>(R.id.et_codigo)
        val nombre = findViewById<EditText>(R.id.et_nombre)
        val titulo = findViewById<EditText>(R.id.et_titulo)

        val save = findViewById<Button>(R.id.saveButton)





        save.setOnClickListener {
            val intent = Intent(this, CrudCarreras::class.java)
            val carrera = Carrera(
                codigo = codigo.text.toString(),
                nombre = nombre.text.toString(),
                titulo = titulo.text.toString()
            )
            carreras.addCarrera(carrera)
            startActivity(intent)
            finish()
        }

    }

}