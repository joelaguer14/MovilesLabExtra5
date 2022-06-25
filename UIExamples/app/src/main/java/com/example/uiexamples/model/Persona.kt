package com.example.uiexamples.model

import java.io.Serializable


class Persona : Serializable {

    var user: String = ""
    var password: String = ""
    var id: String = ""
    var matriculador: Boolean = true
    var profile: String = ""


    internal constructor(user: String, password: String, matriculador: Boolean, id: String, profile: String) {
        this.user = user
        this.password = password
        this.matriculador = matriculador
        this.id = id
        this.profile = profile

    }

}