package Entidades

class Cancha (var numeroCancha:Int, var descripcion:String,  var metroCuadrados:Double,var precio:Double){

    var numCan=numeroCancha
    var desc=descripcion
    var metroCuad=metroCuadrados
    var prec=precio





    override fun toString(): String {
        return "${numeroCancha};${descripcion};$metroCuadrados;$precio"
    }
}