fun main(args: Array<String>) {
        //Variables
        //Que es mutar?

        //Mutables
        var nombre= "Jorge"
        nombre= "Zambrano"

        //Inmutables

        val nombreI= "Jorge"
        //nombreI="Zambrano"

         //tipo de datos

        val apellido = "Zambrano"
        val edad =29
        val sueldo =1.21
        val casado= false
        val profesor= true
        val hijos= null

        // Para entender el tipo de datis se utiliza Duck typing
        //Si parece pato
        //Si camina como pato
        //Si suena como pato
        //Si vuela como pato
        //Pato

        //Condicionles

        if(apellido == "Zambrano" || nombre == "Jorge"){
                println("Verdadero")
        }else{
                println("Falso")
        }

        val nombreYApellido = if(apellido!=null && nombre!= null) "ok" else "no"
        println(nombreYApellido)
        estaJalado(1.0)
        estaJalado(8.0)
        estaJalado(0.0)
        estaJalado(7.0)
        estaJalado(10.0)



}

fun estaJalado(nota:Double): Double{
        when (nota){
                7.0 ->{
                  println("Pasaste con las justas")
                }
                10.0 ->{
                   println("Genial Felicitaciones")
                }
                0.0 ->{
                       println("Que vago")
                }
                else ->{
                        println("Tu nota es: $nota")
                }

        }
        return nota
}