package com.example.uiexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JobAppView : AppCompatActivity() {
    var personas: Personas = Personas.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_app_view)

        val bundle = intent.extras
        val l = bundle?.getSerializable("Persona") as Persona

        val tvusername =  findViewById<TextView>(R.id.text_Nombre)
        val tvuserId =  findViewById<TextView>(R.id.txt_id)
        val tvaddress =  findViewById<TextView>(R.id.addres_txt)
        val tvemail =  findViewById<TextView>(R.id.email_txt)
        val tvphone =  findViewById<TextView>(R.id.phone_txt)
        val tvposition =  findViewById<TextView>(R.id.position_txt)
        val tvdate =  findViewById<TextView>(R.id.txt_startDate)

        val back =  findViewById<Button>(R.id.btnBack)




        tvusername.text = l.nombre
        tvuserId.text = l.id
        tvaddress.text = l.address
        tvemail.text = l.email
        tvphone.text = l.phone
        tvposition.text = l.position
        tvdate.text = l.startDate

        back.setOnClickListener{

            val i = Intent(this, MenuExample::class.java)
            i.putExtra("Login", l)
            finish()
            startActivity(i)
        }
    }
}