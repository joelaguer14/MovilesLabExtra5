package com.example.uiexamples

import java.io.Serializable


class Persona : Serializable {

    var user:String = ""
    var password:String = ""
    var nombre:String = ""
    var foto:Int = 0
    var id:String=""
    var address:String=""
    var email:String=""
    var phone:String=""
    var profile:String=""


    internal constructor(user:String, password:String, nombre:String,id:String, address:String,email:String,phone:String,profile:String, foto:Int){
        this.user = user
        this.password = password
        this.nombre = nombre
        this.foto = foto
        this.id=id
        this.address=address
        this.email=email
        this.phone=phone
        this.profile=profile
    }

}