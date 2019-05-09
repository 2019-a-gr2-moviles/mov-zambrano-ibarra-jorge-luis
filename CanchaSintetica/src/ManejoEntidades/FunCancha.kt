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


public var archivo = File("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cancha.txt")

fun IngresarCancha (indiceCancha: Int, descipcionIng:String, metrosCuadradosIng:Double, precioIng:Double):Unit {

    val nuevaCancha: Cancha = Cancha(indiceCancha,descipcionIng, metrosCuadradosIng, precioIng)

    BaseDeDatos.Cancha.add(nuevaCancha)



    var writer: FileWriter? = null
    val canchaList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;
    canchaList.add("\n"+nuevaCancha.toString().replace("[\\[|\\]]".toRegex(), "")+"\n")



    try {
        archivo.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivo, true)
        writer.append(canchaList.toString().replace("[\\[|\\]]".toRegex(), ""))
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
fun eliminarCancha ( canchaEliminada:String):Unit {

    archivo.writeText("")
    var writer: FileWriter? = null
    val canchaList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;



    try {
        archivo.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivo, true)
        writer.append(canchaEliminada.replace("[,|\\[|\\]]","")+"\n")
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
fun ActualizarCancha ( canchaActualizada:String):Unit {

    archivo.writeText("")

    var writer: FileWriter? = null
    val canchaList: ArrayList<String> = ArrayList<String>(5)
    val disponible=true;



    try {
        archivo.createNewFile()//Si y solo si archivo no existe
        writer = FileWriter(archivo, true)
        writer.append(canchaActualizada.replace("[,|\\[|\\]]","")+"\n")
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
fun listaCancha ():Unit {


    val canchaList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cancha.txt"))
    var temp = ""
    var tempSplit= List<String>(4,{ i -> "Number of index: \$i" });

    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        tempSplit=temp.split(";")

        BuscarCancha.model.addRow(tempSplit.stream().toArray())

        // canchaList.fill(temp);
        //BuscarCancha.model.addRow(canchaList) //guardado el texto del archivo
    }



}
fun listaCanchaAct ():Unit {


    val canchaList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cancha.txt"))
    var temp = ""
    var tempSplit= List<String>(4,{ i -> "Number of index: \$i" });

    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        tempSplit=temp.split(";")

        ActualizarCancha.model.addRow(tempSplit.stream().toArray())

        // canchaList.fill(temp);
        //BuscarCancha.model.addRow(canchaList) //guardado el texto del archivo
    }


}
fun listaCanchaEli ():Unit {


    val canchaList = Array<String>(1, { i -> "Number of index: \$i" })
    val bf = BufferedReader(FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cancha.txt"))
    var temp = ""
    var tempSplit= List<String>(4,{ i -> "Number of index: \$i" });

    while (bf.readLine() != null) {
        temp=  bf.readLine()+"\n"
        tempSplit=temp.split(";")

        EliminarCancha.model.addRow(tempSplit.stream().toArray())

        // canchaList.fill(temp);
        //BuscarCancha.model.addRow(canchaList) //guardado el texto del archivo
    }



}

fun listarCancha ():List<Cancha>{
    return BaseDeDatos.Cancha.filter{cancha-> cancha.numeroCancha>=1 }
}

fun listarCanchaPorNumero (indiceCancha: Int):List<Cancha>{
    return BaseDeDatos.Cancha.filter {  cancha -> cancha.numeroCancha == indiceCancha}
}




