package com.example.jorge.examen

class BDEquipoFutbol{
    companion object {
        val LST_EQUIPO:ArrayList<EquipoFutbol> = ArrayList();
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            nombreUsuario = nombre;
        }

        fun agregarEquipo(equipo: EquipoFutbol):ArrayList<EquipoFutbol>{
            equipo.id = serial
            serial++
            LST_EQUIPO.add(equipo)
            return LST_EQUIPO
        }

        fun mostrarEquipo(): List<EquipoFutbol> {
            return LST_EQUIPO
        }

        fun eliminarEquipo(id:Int){
            LST_EQUIPO.removeAll{ it.id == id }
        }

        fun actualizarEquipo(equipo: EquipoFutbol){
            val indice = LST_EQUIPO.indexOfFirst { it.id == equipo.id }
            LST_EQUIPO[indice] = equipo
        }

    }

}