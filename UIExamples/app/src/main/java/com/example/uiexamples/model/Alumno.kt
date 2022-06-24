package com.example.uiexamples.model

import java.io.Serializable

class Alumno: Serializable {

    var cedula:String=""
    var nombre:String=""
    var telefono:String=""
    var email:String=""
    var fecNac:String=""
    var carrera: Carrera? = null

    internal constructor(cedula:String, nombre:String, telefono:String, email:String, fecNac:String, carrera: Carrera?){
        this.cedula=cedula
        this.nombre=nombre
        this.telefono=telefono
        this.email=email
        this.fecNac=fecNac
        this.carrera=carrera
    }

}