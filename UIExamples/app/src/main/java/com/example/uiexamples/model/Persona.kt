package com.example.uiexamples.model

import java.io.Serializable


class Persona : Serializable {

    var user:String = ""
    var password:String = ""
    var nombre:String = ""
    var foto:Int = 0
    var id:String=""

    var profile:String=""



    internal constructor(user:String, password:String, nombre:String,id:String,profile:String, foto:Int){
        this.user = user
        this.password = password
        this.nombre = nombre
        this.foto = foto
        this.id=id
        this.profile=profile

    }

}