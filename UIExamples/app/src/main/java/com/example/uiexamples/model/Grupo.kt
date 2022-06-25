package com.example.uiexamples.model

import java.io.Serializable

class Grupo: Serializable {

    var numero: String = ""
    var profesor: String = ""
    var horario: String = ""
    var curso: String = ""
    var listaMatriculas: ArrayList<Matricula> = ArrayList<Matricula>()


    internal constructor(numero: String, profesor: String, curso: String,horario: String) {
        this.numero = numero
        this.profesor = profesor
        this.curso = curso
        this.horario = horario
    }

}