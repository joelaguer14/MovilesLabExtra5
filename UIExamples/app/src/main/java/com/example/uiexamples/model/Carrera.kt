package com.example.uiexamples.model

class Carrera:java.io.Serializable {
    var codigo:String = ""
    var nombre:String = ""
    var titulo:String = ""
    var courseList: ArrayList<Curso> = ArrayList<Curso>()


    internal constructor(codigo:String, nombre:String, titulo:String){
        this.codigo = codigo
        this.nombre = nombre
        this.titulo = titulo
    }
}