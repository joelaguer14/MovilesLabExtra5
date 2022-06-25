package com.example.uiexamples.model

import com.example.uiexamples.R

class Matriculas {

    private var matriculas: ArrayList<Matricula> = ArrayList<Matricula>()

    init{
        addMatricula(Matricula("533330954", "EIF160","0"))
        addMatricula(Matricula("533330954", "EIF160","0"))
        addMatricula(Matricula("422220544", "EIF255","0"))
        addMatricula(Matricula("311110568", "EIF255","0"))
        addMatricula(Matricula("217890147", "EIF255","0"))
        addMatricula(Matricula("217890147", "EIF450","0"))
        addMatricula(Matricula("533330954", "EIF450","0"))
        addMatricula(Matricula("118920994", "EIF300","0"))

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

    fun getMatriculas(estud: String): Matricula? {
        for (m: Matricula in matriculas!!){
            if(m.estudiante.equals(estud)){
                return m;
            }
        }
        return null;
    }

    fun getMatriculas(): ArrayList<Matricula>{
        return this.matriculas!!
    }


    fun getPosition(p: Matricula?): Int?{
        return matriculas.indexOf(p)
    }

    fun getMatriculasByStudent(id: String): List<Matricula> {
        return matriculas.filter { m -> m.estudiante.equals(id) };
    }
}