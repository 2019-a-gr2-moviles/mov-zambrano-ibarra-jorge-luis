import Entidades.Cancha
import Entidades.Cliente
import Entidades.Usuario
import Entidades.Reserva

class BaseDeDatos{
    companion object {
        val Usuario:ArrayList<Usuario> = ArrayList()
        val Cancha:ArrayList<Cancha> = ArrayList()
        val Reserva:ArrayList<Reserva> = ArrayList()
        val Cliente:ArrayList<Cliente> = ArrayList()
    }
}