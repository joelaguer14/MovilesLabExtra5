package com.example.uiexamples

import com.example.uiexamples.ui.Login

class Personas private constructor() {

    private var personas: ArrayList<Persona> = ArrayList<Persona>()

    init{
        addPersona(Persona("joel", "123","Joel","116620535" ,"Puris","joel@gmail.com","212121212","admin",R.drawable.foto03,"22/09/2019","junior Dev"))
        addPersona(Persona("alo", "123","Alonso","116880486" ,"santa Ana","alonso@gmail.com","60021119","viewer",R.drawable.foto01,"22/09/2018","senior Dev"))
        addPersona(Persona("jua", "123","Juana","445566778" ,"Alajuelita","juana@gmail.com","8844551100","viewer",R.drawable.foto07,"22/09/2015","web Dev"))
        addPersona(Persona("luis", "123","Luis","334455667" ,"San Carlos","Luis@gmail.com","87502641","viewer",R.drawable.foto04,"22/09/2020","android Dev"))
        addPersona(Persona("jen", "123","Jennifer","224455667" ,"Perez Zeledon","jen@gmail.com","86574103","admin",R.drawable.foto05,"22/09/2021","full stack Dev"))
        addPersona(Persona("cris", "123","Cristina","113344556" ,"Cartago","cris@gmail.com","64521036","viewer",R.drawable.foto02,"22/09/2007","DBA"))
        addPersona(Persona("haz", "123","Hazel","223344556" ,"Heredia","Haz@gmail.com","68752103","admin",R.drawable.foto07,"22/09/2022","Cloud Manager"))
        addPersona(Persona("car", "123","Carlos","112233445" ,"Alajuela","car@gmail.com","70324968","viewer",R.drawable.foto06,"22/09/2000","CyberSecurity SeniorManager"))

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
        aux.startDate = p.startDate
        aux.email = p.email
        aux.address = p.address
        aux.id = p.id
        aux.phone = p.phone
        aux.position = p.position
    }
}