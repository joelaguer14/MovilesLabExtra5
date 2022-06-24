package com.example.uiexamples.model

import com.example.uiexamples.R

class Profesores private constructor() {

    private var profesores: ArrayList<Profesor> = ArrayList<Profesor>()

    init{
        addProfesor(Profesor("118920994", "Luis","88881111","Luis@una.cr" ))
        addProfesor(Profesor("217890147", "David","88882222","david@una.cr" ))
        addProfesor(Profesor("311110568", "Geovanna","88883333","Geovanna@una.cr" ))
        addProfesor(Profesor("422220544", "Maria","88884444","Maria@una.cr" ))
        addProfesor(Profesor("533330954", "Juan","88885555","Juan@una.cr" ))
        addProfesor(Profesor("644440875", "Karen","88886666","Karen@una.cr" ))
        addProfesor(Profesor("755550721", "Jose", "88887777","Jose@una.cr" ))
        addProfesor(Profesor("166660548", "Isabel","88888888","Isabel@una.cr" ))

    }

    private object HOLDER {
        val INSTANCE = Profesores()
    }

    companion object {
        val instance: Profesores by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addProfesor(profesor: Profesor){
        profesores?.add(profesor)
    }

    fun getProfesor(busqueda: String): Profesor? {
        for (p: Profesor in profesores!!){
            if(p.nombre == busqueda || p.cedula == busqueda){
                return p;
            }
        }
        return null;
    }

    fun getProfesores(): ArrayList<Profesor>{
        return this.profesores!!
    }

    fun getPosition(p: Profesor?): Int?{
        return profesores.indexOf(p)
    }

    fun deleteProfesor(position: Int){
        profesores!!.removeAt(position)
    }

    fun editProfesor(p: Profesor, position: Int){
        var aux = profesores!!.get(position)
        aux.cedula = p.cedula
        aux.nombre = p.nombre
        aux.telefono = p.telefono
        aux.email = p.email

    }
}