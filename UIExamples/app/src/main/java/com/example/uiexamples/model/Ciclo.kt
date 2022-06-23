package com.example.uiexamples.model

class Ciclo {
    var annio:String = ""
    var numero:String = ""
    var fechaInicio:String = ""
    var fechaFin:String = ""
    var estado:Boolean= false


    internal constructor(annio:String, numero:String, fechaInicio:String,fechaFin:String,estado:Boolean){
        this.annio = annio
        this.numero = numero
        this.fechaInicio = fechaInicio
        this.fechaFin = fechaFin
        this.estado=estado
    }
}