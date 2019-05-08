package Entidades

class Usuario(var nombreUsuario: String){
    override fun toString(): String {
        return "Usuario. Nombre: ${this.nombreUsuario}"
    }
}