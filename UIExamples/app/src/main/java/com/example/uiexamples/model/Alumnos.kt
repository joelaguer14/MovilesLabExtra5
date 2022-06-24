package com.example.uiexamples.model

class Alumnos private constructor() {

    private var alumnos: ArrayList<Alumno> = ArrayList<Alumno>()

    init {
        addAlumno(
            Alumno(
                "118920994",
                "Luis",
                "88881111",
                "Luis@una.cr",
                "26/10/1990",
                Carreras.instance.getCarrera("EIF")!!
            )
        )
        addAlumno(
            Alumno(
                "217890147",
                "David",
                "88882222",
                "david@una.cr",
                "26/11/1994",
                Carreras.instance.getCarrera("MAT")!!
            )
        )
        addAlumno(
            Alumno(
                "311110568",
                "Geovanna",
                "88883333",
                "Geovanna@una.cr",
                "15/06/1996",
                Carreras.instance.getCarrera("VET")!!
            )
        )
        addAlumno(
            Alumno(
                "422220544",
                "Maria",
                "88884444",
                "Maria@una.cr",
                "06/01/1992",
                Carreras.instance.getCarrera("EDU")!!
            )
        )
        addAlumno(
            Alumno(
                "533330954",
                "Juan",
                "88885555",
                "Juan@una.cr",
                "14/02/1980",
                Carreras.instance.getCarrera("EIF")!!
            )
        )
        addAlumno(
            Alumno(
                "644440875",
                "Karen",
                "88886666",
                "Karen@una.cr",
                "12/04/1975",
                Carreras.instance.getCarrera("MAT")!!
            )
        )
        addAlumno(
            Alumno(
                "755550721",
                "Jose",
                "88887777",
                "Jose@una.cr",
                "04/07/1976",
                Carreras.instance.getCarrera("EDU")!!
            )
        )
        addAlumno(
            Alumno(
                "166660548",
                "Isabel",
                "88888888",
                "Isabel@una.cr",
                "30/12/1982",
                Carreras.instance.getCarrera("VET")!!
            )
        )

    }

    private object HOLDER {
        val INSTANCE = Alumnos()
    }

    companion object {
        val instance: Alumnos by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addAlumno(alumno: Alumno) {
        alumnos.add(alumno)
    }

    fun getAlumno(busqueda: String): Alumno? {
        for (a: Alumno in alumnos) {
            if (a.nombre == busqueda || a.cedula == busqueda || (a.carrera!!.nombre.contains(busqueda))) {
                return a
            }
        }
        return null
    }

    fun getAlumnos(): ArrayList<Alumno> {
        return this.alumnos
    }

    fun getPosition(p: Alumno?): Int? {
        return alumnos.indexOf(p)
    }

    fun deleteAlumno(position: Int) {
        alumnos.removeAt(position)
    }

    fun editAlumno(a: Alumno, position: Int) {
        var aux = alumnos.get(position)
        aux.cedula = a.cedula
        aux.nombre = a.nombre
        aux.telefono = a.telefono
        aux.email = a.email
        aux.fecNac = a.fecNac


    }
}