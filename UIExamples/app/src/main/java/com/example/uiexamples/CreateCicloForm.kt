package com.example.uiexamples

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.uiexamples.model.Ciclo
import com.example.uiexamples.model.Ciclos
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas

class CreateCicloForm : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ciclo_form)

        var ciclos: Ciclos = Ciclos.instance

        val annio = findViewById<EditText>(R.id.et_annio)
        val fechaInicio = findViewById<EditText>(R.id.et_fechainicio)
        val fechaFin = findViewById<EditText>(R.id.et_fehcafin)
        val numero = findViewById<AutoCompleteTextView>(R.id.atv_numero)
        val save = findViewById<Button>(R.id.saveButton)

        cargarAutoCompleteTextView()


        save.setOnClickListener {
            val intent = Intent(this, CrudCiclos::class.java)
            val ciclo = Ciclo(
                annio = annio.text.toString(),
                fechaInicio = fechaInicio.text.toString(),
                fechaFin = fechaFin.text.toString(),
                estado = false,
                numero = numero.text.toString(),

            )
            ciclos.addCiclo(ciclo)
            finish()
            startActivity(intent)
        }

    }

    private fun cargarAutoCompleteTextView() {
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.atv_numero)
        val profiles: Array<String> = arrayOf("1ero", "2ndo")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, profiles)
        autoCompleteTextView.threshold = 0
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnFocusChangeListener({ view, b -> if (b) autoCompleteTextView.showDropDown() })
    }
}