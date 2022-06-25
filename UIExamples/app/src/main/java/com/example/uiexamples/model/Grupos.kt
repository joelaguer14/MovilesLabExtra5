package com.example.uiexamples.model

class Grupos {

    private var grupos: ArrayList<Grupo> = ArrayList<Grupo>()

    init{
        var matriculas: Matriculas = Matriculas.instance

        val grupo1 =  Grupo("001","533330954","ADM100","8:00-9:40am")
        val grupo2 =  Grupo("007","533330954","ADM100","1:00-4:40pm")
        val grupo3 =  Grupo("008","422220544","EIF160","5:00-6:40pm")
        val grupo4 =  Grupo("078","422220544","EIF160","10:00-11:40am")
        val grupo5 =  Grupo("051","311110568","EIF255","8:00-11:40am")
        val grupo6 =  Grupo("061","311110568","EIF255","1:00-4:40pm")

        grupo1.listaMatriculas.add(matriculas.getMatriculas().get(0))
        grupo1.listaMatriculas.add(matriculas.getMatriculas().get(1))
        grupo2.listaMatriculas.add(matriculas.getMatriculas().get(2))
        grupo2.listaMatriculas.add(matriculas.getMatriculas().get(3))
        grupo2.listaMatriculas.add(matriculas.getMatriculas().get(4))
        grupo3.listaMatriculas.add(matriculas.getMatriculas().get(5))
        grupo3.listaMatriculas.add(matriculas.getMatriculas().get(6))
        grupo3.listaMatriculas.add(matriculas.getMatriculas().get(7))

        addGrupo(grupo1)
        addGrupo(grupo2)
        addGrupo(grupo3)
        addGrupo(grupo4)
        addGrupo(grupo5)
        addGrupo(grupo6)


    }

    private object HOLDER {
        val INSTANCE = Grupos()
    }

    companion object {
        val instance: Grupos by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addGrupo(grupo: Grupo){
        grupos?.add(grupo)
    }

    fun getGrupo(numero: String): Grupo? {
        for (c: Grupo in grupos!!){
            if(c.numero.equals(numero)){
                return c;
            }
        }
        return null;
    }

    fun getGrupos(): ArrayList<Grupo>{
        return this.grupos!!
    }


    fun getPosition(p: Grupo?): Int?{
        return grupos.indexOf(p)
    }

    fun deleteGrupo(position: Int){
        grupos!!.removeAt(position)
    }

    fun editGrupo(g: Grupo, position: Int){
        var aux = grupos!!.get(position)
        aux.numero = g.numero
        aux.curso = g.curso
        aux.profesor = g.profesor
    }

}