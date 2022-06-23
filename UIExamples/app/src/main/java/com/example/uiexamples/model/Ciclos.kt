package com.example.uiexamples.model

class Ciclos {

    private var ciclos: ArrayList<Ciclo> = ArrayList<Ciclo>()

    init{
        addCiclo(Ciclo("2021", "1","14/02/21","23/06/21",false))
        addCiclo(Ciclo("2021", "2","15/07/21","30/11/21",false))
        addCiclo(Ciclo("2022", "1","14/02/22","15/06/22",false))
        addCiclo(Ciclo("2022", "2","01/07/22","01/12/22",true))


    }

    private object HOLDER {
        val INSTANCE = Ciclos()
    }

    companion object {
        val instance: Ciclos by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addCiclo(ciclo: Ciclo){
        ciclos?.add(ciclo)
    }

    fun getCiclo(numero: String,annio: String): Ciclo? {
        for (c: Ciclo in ciclos!!){
            if(c.numero.equals(numero) && c.annio.equals(annio)){
                return c;
            }
        }
        return null;
    }

    fun getCiclos(): ArrayList<Ciclo>{
        return this.ciclos!!
    }


    fun getPosition(p: Ciclo?): Int?{
        return ciclos.indexOf(p)
    }

    fun deleteCiclo(position: Int){
        ciclos!!.removeAt(position)
    }

    fun editCiclo(c: Ciclo, position: Int){
        var aux = ciclos!!.get(position)
        aux.numero = c.numero
        aux.annio = c.annio
        aux.fechaFin = c.fechaFin
        aux.fechaInicio = c.fechaInicio
        aux.estado = c.estado

    }
}