package com.example.uiexamples.model

import com.example.uiexamples.R

class Cursos private constructor() {

    private var cursos: ArrayList<Curso> = ArrayList<Curso>()

    init{
        addCurso(Curso("EIF300", "Java Avanzado","3","5" ))
        addCurso(Curso("EIF450", "Moviles","4","6" ))
        addCurso(Curso("EIF255", "POO","3","5" ))
        addCurso(Curso("EIF160", "Arquitectura","3","5" ))
        addCurso(Curso("ADM100", "Finanzas","2","3" ))
        addCurso(Curso("FIL288", "Sócrates","3","5" ))
        addCurso(Curso("BIO246", "MicroBiologíaI","4","6" ))
        addCurso(Curso("EIF194", "PPS","2","3" ))

    }

    private object HOLDER {
        val INSTANCE = Cursos()
    }

    companion object {
        val instance: Cursos by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addCurso(curso: Curso){
        cursos?.add(curso)
    }

    fun getCurso(nombre: String): Curso? {
        for (c: Curso in cursos!!){
            if(c.nombre.equals(nombre)){
                return c;
            }
        }
        return null;
    }

    fun getCursos(): ArrayList<Curso>{
        return this.cursos!!
    }

    fun getPosition(p: Curso?): Int?{
        return cursos.indexOf(p)
    }

    fun deleteCurso(position: Int){
        cursos!!.removeAt(position)
    }

    fun editCurso(c: Curso, position: Int){
        var aux = cursos!!.get(position)
        aux.codigo = c.codigo
        aux.nombre = c.nombre
        aux.creditos = c.creditos
        aux.horas = c.horas

    }
}