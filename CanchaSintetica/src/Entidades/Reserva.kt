package Entidades

import java.util.*

class Reserva(val cliente: String, val fechaReservaInicial: String, val fechaReservaFinal: String, val valorAPagar: Double, val cancha:Int){
    override fun toString(): String {
        return "${cliente};$fechaReservaInicial;$fechaReservaFinal;$valorAPagar;${cancha}"
    }
}
