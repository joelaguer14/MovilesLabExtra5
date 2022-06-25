package com.example.uiexamples.model

import com.example.uiexamples.R

class Personas private constructor() {

    private var personas: ArrayList<Persona> = ArrayList<Persona>()

    init{
        addPersona(Persona("joel", "123",true,"116620535" ,"Profesor"))
        addPersona(Persona("alo", "123",true,"116880486" ,"Administrador"))
        addPersona(Persona("jua", "123",false,"445566778" ,"Alumno"))
        addPersona(Persona("luis", "123",false,"334455667" ,"Profesor"))
        addPersona(Persona("jen", "123",true,"224455667" ,"Administrador"))
        addPersona(Persona("cris", "123",false,"113344556" ,"Alumno"))
        addPersona(Persona("haz", "123",true,"223344556" ,"Profesor"))
        addPersona(Persona("car", "123",true,"112233445" ,"Administrador"))

    }

    private object HOLDER {
        val INSTANCE = Personas()
    }

    companion object {
        val instance: Personas by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addPersona(persona: Persona){
        personas?.add(persona)
    }

    fun getPersona(search: String): Persona? {
        for (p: Persona in personas!!){
            if(p.user.equals(search)){
                return p;
            }
        }
        return null;
    }

    fun getPersonas(): ArrayList<Persona>{
        return this.personas!!
    }

    fun login(user: String?, password: String?): Boolean{
        for(p: Persona in personas!!){
            if(p.user.equals(user) && p.password.equals(password)){
                return true
            }
        }
        return false
    }

    fun loginP(user: String?, password: String?): Persona?{
        for(p: Persona in personas!!){
            if(p.user.equals(user) && p.password.equals(password)){
                return p
            }
        }
        return null
    }

    fun getPosition(p: Persona?): Int?{
        return personas.indexOf(p)
    }

    fun deletePerson(position: Int){
        personas!!.removeAt(position)
    }

    fun editPerson(p: Persona, position: Int){
        var aux = personas!!.get(position)
        aux.password = p.password
        aux.matriculador = p.matriculador
        aux.user = p.user

        aux.id = p.id

    }
}