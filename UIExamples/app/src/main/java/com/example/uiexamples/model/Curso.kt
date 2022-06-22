package com.example.uiexamples.model

import java.io.Serializable

class Curso: Serializable {

    var codigo:String=""
    var nombre:String=""
    var creditos:String=""
    var horas:String=""

    internal constructor(codigo:String, nombre:String, creditos:String, horas:String){
        this.codigo=codigo
        this.nombre=nombre
        this.creditos=creditos
        this.horas=horas
    }

}