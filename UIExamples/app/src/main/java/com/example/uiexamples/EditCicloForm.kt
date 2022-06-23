package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.uiexamples.model.Ciclo
import com.example.uiexamples.model.Ciclos
import com.example.uiexamples.model.Persona
import com.example.uiexamples.model.Personas

class EditCicloForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ciclo_form)
        var ciclos: Ciclos = Ciclos.instance
        val bundle = intent.extras
        val position = bundle!!.getInt("position")
        val recivedCiclo =  bundle.getSerializable("Ciclo") as Ciclo

        val annio =  findViewById<EditText>(R.id.et_annio)
        val numero =  findViewById<EditText>(R.id.et_numero)
        val fechaInicio =  findViewById<EditText>(R.id.et_fechainicio)
        val fechaFin =  findViewById<EditText>(R.id.et_fechafin)

        val save =  findViewById<Button>(R.id.saveButton)



        annio.setText(recivedCiclo.annio)
        numero.setText(recivedCiclo.numero)
        fechaInicio.setText(recivedCiclo.fechaInicio)
        fechaFin.setText(recivedCiclo.fechaFin)

        save.setOnClickListener{
            val intent = Intent(this, CrudCiclos::class.java)
            recivedCiclo.annio = annio.text.toString()
            recivedCiclo.numero = numero.text.toString()
            recivedCiclo.fechaInicio = fechaInicio.text.toString()
            recivedCiclo.fechaFin = fechaFin.text.toString()
            ciclos.editCiclo(recivedCiclo,position)
            startActivity(intent)
            finish()
        }

    }
}