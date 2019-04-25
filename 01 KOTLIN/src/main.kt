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
        holaMundo("Jorge")
        holaMundoAvanzado(2)
        val total=sumarDosNumeros(1,2)
        println(total)

        val arregloCumpleanos: Array<Int> = arrayOf(1,2,3,4)
        var arregloTodo: Array<Any> = arrayOf(1,"asd",2.2,true)
        arregloCumpleanos.set(0,5)
        arregloTodo= arrayOf(5,2,3,4)

        var notas = arrayListOf(1,2,3,4,5,6)
        

        //forEach -> itera el arreglo
        notas.forEach { nota: Int ->
                println(nota)
        }

        notas.forEachIndexed(){ indice, nota ->
                println("Indice:$indice")
                println("Nota: $nota")

        }

        //map -> itera y modififca el arreglo
        val notaDos= notas.map { nota->
                nota +1
        }

        notaDos.forEach{
                println("Notas 2: $it")
        }
        //Impares +1  Pares +2
        val notaTres= notas.map { nota->
                val mod= nota%2
                val esPar=0
                when (mod){
                        esPar->{
                                nota+2
                        }
                        else ->{
                                nota+1
                        }
                }
        }

        notaTres.forEach{
                println("Notas 3: $it")
        }

        val respuestaFilter = notas.filter{
                it in 3..4 //it>2&&it<5

        }.map {
                it*2
        }

        respuestaFilter.forEach{
                println(it)
        }

        val novias = arrayListOf(1,2,2,3,4,5)

        val respuestaNovia =novias.any {
                it==3
        }

        println(respuestaNovia)

        val tazos = arrayListOf(1,2,3,4,5,6,7)
        val respuestaTazos= tazos.all {
                it>1
        }
        println(respuestaTazos)

        val respuestaTazos1 = tazos.reduce { valorAcum, tazo ->
                        valorAcum+tazo
        }
        println(respuestaTazos1)

        //val fecha = Date()
        //fecha.time=1112321
        //sumarDosNumeros(numUno:1,numDos:2)
        //numUno y numDos lo pone el editor
        val numerito =Numero("1")


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
fun holaMundo(mensaje:String):Unit{
        println("Mensaje: $mensaje.")

}
fun holaMundoAvanzado(mensaje:Any):Unit{
        println("Mensaje: $mensaje.")

}
fun sumarDosNumeros(numUno: Int, numDos: Int): Int{
        return numUno+numDos
}

class Usuario(val cedula:String){
        public var nombre:String=""
        public var apellido:String=""

        constructor(cedulaM:String,apellido:String):this(cedulaM){
                this.apellido= apellido
        }
}

class UsuarioKT( var nombre:String,  var apellido: String, private var id:Int,private var edad:Int){
        fun hola():String{
            return this.apellido;
        }

        private fun hola2(){

        }

        protected fun hola3(){

        }

        companion object {
                val gravedad=10.5
                fun correr(){
                        println("Estoy corriendo en $gravedad")
                }
        }
}

class BaseDeDatos{
        companion object {
            val usuarios= arrayListOf(1,2,3)
            fun agregarUsuario(usuario:Int){
                    this.usuarios.add(usuario)
            }
        }
}

fun aa(){
        UsuarioKT.gravedad
        UsuarioKT.correr()
}
fun a(){
        val jorge = UsuarioKT("a","b",1,1)
        jorge.nombre="jorge luis"

        val jorge2= Usuario("a")
        jorge2.apellido="as"

}

class Numero(var numero:Int){
        constructor(numeroString:String):this(numeroString.toInt()){
                println("CONSTRUCTOR")
        }
        init {
                println("INIT")
        }
}

class A(){
       //A.correr() //metodo estatico
        // A.gravedad //propiedad estatica
}

//clases abstractas--> no se instancian

abstract class Numeros(var numeroUno:Int, var numeroDos:Int){

}
class Suma (numeroUnos:Int,
            numeroDoss:Int):Numeros(numeroUnos,numeroDoss){

}
fun cc(){
        val a=Suma(1,2);
}
fun presley(requerido:Int,
            opcional:Int=1,
            nulo:UsuarioKT?)/*a veces es nulo*/{
        if(nulo!=null) {
                nulo.nombre
        }
        val nombresito = nulo?.nombre
        nulo!!.nombre
}
fun cdddd(){
        presley(requerido=1,nulo = 0)// named parameters para saltarse los opcionales
        presley(1,1,0)
        presley(1,1,null)

}