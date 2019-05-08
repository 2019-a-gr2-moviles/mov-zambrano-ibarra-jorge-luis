package Entidades

class Cliente(var cedulaCliente: String, var nombreCliente:String,var telefonoCliente:String, var direccionCliente:String){
    override fun toString(): String {
        return "Cedula: ${this.cedulaCliente} Apellido: ${nombreCliente} Telefono: $telefonoCliente Direccion: $direccionCliente"
    }
}