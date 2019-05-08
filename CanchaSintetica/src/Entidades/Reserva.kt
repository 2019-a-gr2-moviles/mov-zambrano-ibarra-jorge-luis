package Entidades

import java.util.*

class Reserva(val cliente: String, val fechaReservaInicial: String, val fechaReservaFinal: String, val valorAPagar: Double, val cancha:Int){
    override fun toString(): String {
        return "Cliente: ${cliente} Fecha Reserva Inicial: $fechaReservaInicial Fecha Reserva Final: $fechaReservaFinal Valor Pagar: $valorAPagar Cancha: ${cancha} "
    }
}
