package ManejoEntidades
import IngresarCancha
import Entidades.Cancha
import BuscarCancha
import EliminarCancha
import BuscarCancha.*
import ActualizarCancha
import Entidades.Cliente
import Entidades.Usuario
import Entidades.Reserva
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import sun.plugin2.liveconnect.ArgumentHelper.writeObject
import java.io.*
import java.util.ArrayList
import javax.swing.JOptionPane
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import java.awt.SystemColor.info
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import java.io.BufferedReader
import java.io.FileReader
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path


public var archivoReserva = File("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\reserva.txt")

fun IngresarReserva (cedulaCliente:String,fechainicial:String,fechaFinal:String,precio:Double,numero:Int):Unit {

    val nuevaReserva: Reserva = Reserva(cedulaCliente,fechainicial,fechaFinal,precio,numero)

    BaseDeDatos.Reserva.add(nuevaReserva)



    var writer: FileWriter? = null
    val reservaList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;
    reservaList.add("\n"+nuevaReserva.toString().replace("[,| \\[ | \\] ]".toRegex(), "")+"\n")



    try {
        archivoReserva.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivoReserva, true)
        writer.append(reservaList.toString().replace("[,| \\[ | \\] ]".toRegex(), ""))
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
fun eliminarReserva ( reservaEliminada:String):Unit {

    archivoReserva.writeText("")
    var writer: FileWriter? = null
    val resrvaList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;



    try {
        archivoReserva.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivoReserva, true)
        writer.append(reservaEliminada.replace("[ \\[ | \\] ]","")+"\n")

        JOptionPane.showMessageDialog(null,"Eliminaado")
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
fun actualizarReserva ( reservaActualizada:String):Unit {

    archivoReserva.writeText("")

    var writer: FileWriter? = null
    val reservaList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;



    try {
        archivoReserva.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivoReserva, true)
        writer.append(reservaActualizada.replace("[ \\[ | \\] ]","")+"\n")
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
fun listaReserva ():Unit {


    val canchaList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\reserva.txt"))
    var temp = ""

    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        canchaList.fill(temp);
        BuscarReserva.model.addRow(canchaList) //guardado el texto del archivo
    }



}
fun listaReservaAct ():Unit {


    val canchaList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\reserva.txt"))
    var temp = ""

    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        canchaList.fill(temp);
        ActualizarReserva.model.addRow(canchaList) //guardado el texto del archivo
    }



}
fun listaReservaEli ():Unit {


    val canchaList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\reserva.txt"))
    var temp = ""

    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        canchaList.fill(temp);
        EliminarReserva.model.addRow(canchaList) //guardado el texto del archivo
    }



}




