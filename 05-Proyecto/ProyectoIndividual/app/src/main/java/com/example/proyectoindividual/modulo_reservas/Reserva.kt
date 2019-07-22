package com.example.proyectoindividual.modulo_reservas

import com.example.proyectoindividual.clases_auxiliares.ClienteAux
import com.example.proyectoindividual.clases_auxiliares.CanchaAux

class Reserva(
    var id: Int?,
    var fecha: String?,
    var horaInicial: Int,
    var horaFinal: Int,
    var codigoCli: ClienteAux?,
    var codigoCancha: CanchaAux?
) {

}