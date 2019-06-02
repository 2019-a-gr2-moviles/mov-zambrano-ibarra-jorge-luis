package com.example.jorge.examen

class BDJugador {
    companion object {
        val LST_JUGADOR:ArrayList<Jugador> = ArrayList();
        var serial:Int= 1;

        fun agregarJugador(jugador: Jugador):ArrayList<Jugador>{
            jugador.id = serial
            serial++
            LST_JUGADOR.add(jugador)
            return LST_JUGADOR
        }

        fun mostrarJugador(padreId:Int): List<Jugador> {
            val lstFiltradaJugador = LST_JUGADOR.filter { it.equipoFutbolId ==  padreId}
            return lstFiltradaJugador
        }

        fun eliminarJugador(id:Int){
            LST_JUGADOR.removeAll{ it.id == id }
        }

        fun actualizarJugador(jugador: Jugador){
            val indice = LST_JUGADOR.indexOfFirst { it.id == jugador.id }
            LST_JUGADOR[indice] = jugador
        }

    }

}