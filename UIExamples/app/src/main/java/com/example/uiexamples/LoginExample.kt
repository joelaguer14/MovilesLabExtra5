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
        // get reference to all views

//        var registerText = findViewById(R.id.textRegister) as TextView
        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_submit = findViewById(R.id.btn_submit) as Button
//        var checked = findViewById<CheckedTextView>(R.id.changePasswordChecked)
//        cargarCheckedTextViews()


//        registerText.setOnClickListener {
//            // clearing user_name and password edit text views on reset button click
//            val intent = Intent(this, CreatePersonForm::class.java)
//            startActivity(intent)
//        }

        // set on-click listener
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
//                }
//                else{
//                    val persona = personas.loginP(user_name.toString(), password.toString())
//                    val i = Intent(this, EditPersonaForm::class.java)
//                    val position = personas.getPosition(persona)
//                    i.putExtra("position", position)
//                    i.putExtra("Persona",persona)
//
//                    finish()
//                    startActivity(i)
//                }
            }else{
                Toast.makeText(this, "El usuario no se encuentra registrado", Toast.LENGTH_SHORT).show()
            }

        }

    }
//    private fun cargarCheckedTextViews(){
//        val checked1 = findViewById<CheckedTextView>(R.id.changePasswordChecked)
//        checked1.isChecked = !checked1.isChecked
//        checked1.setCheckMarkDrawable(android.R.drawable.checkbox_off_background)
//        checked1.setOnClickListener {
//            checked1.isChecked = !checked1.isChecked
//
//            checked1.setCheckMarkDrawable(
//                if (checked1.isChecked){
//                    android.R.drawable.checkbox_off_background
//                }
//                else{
//                    android.R.drawable.checkbox_on_background
//                }
//            )
//        }
//    }

}