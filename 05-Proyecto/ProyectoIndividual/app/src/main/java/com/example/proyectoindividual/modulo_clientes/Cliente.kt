package com.example.proyectoindividual.modulo_clientes

class Cliente(
//    var compraDeCliente: ArrayList<CompraAux>?,
    var createdAt: Long?,
    var updatedAt: Long?,
    var id: Int?,
    var nombre: String,
    var cedula: String,
    var telefono: String,
    var direccion: String
) {

}


//parcel.readSerializable() as? ArrayList<Reserva>,
//parcel.writeSerializable(compraDeCliente)