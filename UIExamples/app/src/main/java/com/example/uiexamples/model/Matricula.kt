package com.example.uiexamples.model

import java.io.Serializable

class Matricula: Serializable {

    var estudiante:String=""
    var curso:String=""
    var nota:String=""


    internal constructor(estudiante:String, curso:String, nota:String){
        this.estudiante=estudiante
        this.curso=curso
        this.nota=nota
    }

}