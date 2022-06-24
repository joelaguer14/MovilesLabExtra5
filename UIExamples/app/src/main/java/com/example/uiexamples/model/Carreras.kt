package com.example.uiexamples.model

class Carreras {

    private var carreras: ArrayList<Carrera> = ArrayList<Carrera>()

    init{
        addCarrera(Carrera("EIF", "Ing en Sistemas","Bachiller"))
        addCarrera(Carrera("MAT", "Matematica Pura","Bachiller"))
        addCarrera(Carrera("EDU", "Educación","Bachiller"))
        addCarrera(Carrera("VET", "Veterinaria","Licenciatura"))
    }

    private object HOLDER {
        val INSTANCE = Carreras()
    }

    companion object {
        val instance: Carreras by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addCarrera(carrera: Carrera){
        carreras?.add(carrera)
    }

    fun getCarrera(codigo: String): Carrera? {
        for (c: Carrera in carreras!!){
            if(c.codigo.equals(codigo)){
                return c;
            }
        }
        return null;
    }

    fun getCarreras(): ArrayList<Carrera>{
        return this.carreras!!
    }

    fun addCourse(codigo: String,curso:Curso){
        getCarrera(codigo)?.courseList?.add(curso)
    }


    fun getPosition(p: Carrera?): Int?{
        return carreras.indexOf(p)
    }

    fun deleteCarrera(position: Int){
        carreras!!.removeAt(position)
    }

    fun editCarrera(c: Carrera, position: Int){
        var aux = carreras!!.get(position)
        aux.codigo = c.codigo
        aux.nombre = c.nombre
        aux.titulo = c.titulo
    }
}