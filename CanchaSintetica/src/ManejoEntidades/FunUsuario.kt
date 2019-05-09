package ManejoEntidades


import Entidades.Cliente
import java.io.*
import java.util.ArrayList
import javax.swing.JOptionPane
import InsertarReserva



public var archivoCliente = File("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cliente.txt")
fun ingresarCliente(cedula:String,nombre:String,telefono:String,direccion:String):Unit {
    val nuevoCliente: Cliente = Cliente(cedula, nombre, telefono, direccion)
    BaseDeDatos.Cliente.add(nuevoCliente)


    var writer: FileWriter? = null
    val clienteList: ArrayList<String> = ArrayList<String>(4)
    val disponible = true;
    clienteList.add("\n" + nuevoCliente.toString().replace("[,|\\[|\\]]".toRegex(), "") + "\n")



    try {
        archivoCliente.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivoCliente, true)
        writer.append(clienteList.toString().replace("[,|\\[|\\]]".toRegex(), ""))
        JOptionPane.showMessageDialog(null,"Guardado")

    } catch (ex: IOException) {
        System.err.println(ex.message)
    } finally {
        if (writer != null) {
            try {
                writer.close()
            } catch (ex: IOException) {
                System.err.println(ex.message)
            }

        }
    }
}
fun listaCliente ():Unit {
    val clienteList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cliente.txt"))
    var temp = ""
    var tempSplit= List<String>(4,{ i -> "Number of index: \$i" });
    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        tempSplit=temp.split(";")

        BuscarCliente.model.addRow(tempSplit.stream().toArray()) //guardado el texto del archivo
    }

}
fun listaClienteAct ():Unit {
    val clienteList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cliente.txt"))
    var temp = ""

    var tempSplit= List<String>(4,{ i -> "Number of index: \$i" });
    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        tempSplit=temp.split(";")

        ActualizarCliente.model.addRow(tempSplit.stream().toArray()) //guardado el texto del archivo
    }

}



fun listaClienteEli ():Unit {
    val clienteList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cliente.txt"))
    var temp = ""

    var tempSplit= List<String>(4,{ i -> "Number of index: \$i" });
    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        tempSplit=temp.split(";")

        EliminarCliente.model.addRow(tempSplit.stream().toArray()) //guardado el texto del archivo
    }



}


fun actualizarCliente ( clienteActualizado:String):Unit {

    archivoCliente.writeText("")

    var writer: FileWriter? = null
    val clienteList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;



    try {
        archivo.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivoCliente, true)
        writer.append(clienteActualizado.replace("[\\[|\\]]","")+"\n")

        JOptionPane.showMessageDialog(null,"Actualizado")
    } catch (ex: IOException) {
        System.err.println(ex.message)
    } finally {
        if (writer != null) {
            try {
                writer.close()
            } catch (ex: IOException) {
                System.err.println(ex.message)
            }

        }
    }



}
fun eliminarClieinte ( clienteEliminado:String):Unit {

    archivoCliente.writeText("")
    var writer: FileWriter? = null
    val clienteList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;



    try {
        archivoCliente.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivoCliente, true)
        writer.append(clienteEliminado.replace("[\\[|\\]]","")+"\n")

        JOptionPane.showMessageDialog(null,"Eliminado")
    } catch (ex: IOException) {
        System.err.println(ex.message)
    } finally {
        if (writer != null) {
            try {
                writer.close()
            } catch (ex: IOException) {
                System.err.println(ex.message)
            }

        }
    }



}



