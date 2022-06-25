package com.example.uiexamples

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.uiexamples.model.Personas

class LoginExample : AppCompatActivity() {

    var personas: Personas = Personas.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_example)

        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text;
            //Toast.makeText(this@LoginExample, user_name, Toast.LENGTH_LONG).show()
            if(personas.login(user_name.toString(), password.toString())){
               // if(checked.isChecked) {
                    val bundle = Bundle()
                    val Login = personas.loginP(user_name.toString(), password.toString())
                    val i = Intent(this, MenuExample::class.java)
                    i.putExtra("Login", Login)

                    finish()
                    startActivity(i)

            }else{
                Toast.makeText(this, "El usuario no se encuentra registrado", Toast.LENGTH_SHORT).show()
            }

        }

    }


}