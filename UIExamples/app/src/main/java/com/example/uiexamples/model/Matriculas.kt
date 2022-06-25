package com.example.uiexamples.model

import android.text.Editable

class Matriculas {

    private var matriculas: ArrayList<Matricula> = ArrayList<Matricula>()

    init{
        addMatricula(Matricula("533330954", "EIF160","85"))
        addMatricula(Matricula("644440875", "EIF160","70"))
        addMatricula(Matricula("422220544", "EIF255","90"))
        addMatricula(Matricula("311110568", "EIF255","100"))
        addMatricula(Matricula("217890147", "EIF255","45"))
        addMatricula(Matricula("217890147", "EIF450","87"))
        addMatricula(Matricula("533330954", "EIF450","65"))
        addMatricula(Matricula("118920994", "EIF300","55"))

    }

    private object HOLDER {
        val INSTANCE = Matriculas()
    }

    companion object {
        val instance: Matriculas by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addMatricula(matricula: Matricula){
        matriculas?.add(matricula)
    }


    fun getMatriculas(): ArrayList<Matricula>{
        return this.matriculas!!
    }
    fun actualizaNota(curso:String?, estudiante:String?, nota: Editable?){
        for (m: Matricula in matriculas!!){
            if(m.estudiante.equals(estudiante) && m.curso.equals(curso)){
                if (nota != null) {
                    m.nota = nota.toString()
                }
            }
        }
    }


    fun getPosition(p: Matricula?): Int?{
        return matriculas.indexOf(p)
    }

    fun getMatriculasByStudent(id: String): List<Matricula> {
        return matriculas.filter { m -> m.estudiante.equals(id) };
    }
}