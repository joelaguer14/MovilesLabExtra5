package com.example.uiexamples.model

import com.example.uiexamples.R

class Personas private constructor() {

    private var personas: ArrayList<Persona> = ArrayList<Persona>()

    init{
        addPersona(Persona("joel", "123","Joel","116620535" ,"admin",R.drawable.foto03))
        addPersona(Persona("alo", "123","Alonso","116880486" ,"viewer",R.drawable.foto01))
        addPersona(Persona("jua", "123","Juana","445566778" ,"viewer",R.drawable.foto07))
        addPersona(Persona("luis", "123","Luis","334455667" ,"viewer",R.drawable.foto04))
        addPersona(Persona("jen", "123","Jennifer","224455667" ,"admin",R.drawable.foto05))
        addPersona(Persona("cris", "123","Cristina","113344556" ,"viewer",R.drawable.foto02))
        addPersona(Persona("haz", "123","Hazel","223344556" ,"admin",R.drawable.foto07))
        addPersona(Persona("car", "123","Carlos","112233445" ,"viewer",R.drawable.foto06))

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

    fun getPersona(nombre: String): Persona? {
        for (p: Persona in personas!!){
            if(p.nombre.equals(nombre)){
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
        aux.nombre = p.nombre
        aux.user = p.user

        aux.id = p.id

    }
}