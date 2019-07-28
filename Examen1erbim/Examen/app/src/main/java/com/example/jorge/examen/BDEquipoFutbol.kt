package com.example.jorge.examen

import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import java.lang.Exception

class BDEquipoFutbol{
    companion object {
        val LST_EQUIPO:ArrayList<EquipoFutbol> = arrayListOf();
        var serial:Int= 1;
        var nombreUsuario:String = "";
        fun guardarUsuario(nombre:String){
            nombreUsuario = nombre;
        }

        fun agregarEquipo(equipo: EquipoFutbol):ArrayList<EquipoFutbol>{
            val url = "http://192.168.43.137:1337/equipo"
            val json = """
            {
            "nombre": "${equipo.nombre}",
            "fechaCreacion": "${equipo.fechaCreacion}",
            "liga": "${equipo.liga}",
            "numeroCopasInternacionales": "${equipo.numeroCopasInternacionales}",
            "campeonActual": "${equipo.campeonActual}"
                        }
                    """

            url.httpPost().body(json)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {

                                LST_EQUIPO.add(equipo)


                        }
                    }
                }
            return LST_EQUIPO
        }

        fun mostrarEquipo(lista:ArrayList<EquipoFutbol> = arrayListOf()): List<EquipoFutbol> {
            try {
                val url = "http://192.168.43.137:1337/equipo"
                Log.i("http", url)
                url.httpGet()
                    .responseString { request, response, result ->
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException()
                                Log.i("http", "Error: ${ex.message}")
                            }
                            is Result.Success -> {
                                val data = result.get()
                                Log.i("http", "Data: ${data}")

                                val equipo = Klaxon()
                                    .parseArray<EquipoFutbol>(data)

                                equipo?.forEach { equipo ->
                                    (
                                            lista.add(equipo)
                                            )
                                }

                            }
                        }
                    }
            } catch (e: Exception) {
                Log.i("http", "${e}")
            }
            return lista
        }

        fun eliminarEquipo(id:Int){
            LST_EQUIPO.removeAll{ it.id == id }
        }

        fun actualizarEquipo(equipo: EquipoFutbol){
            val indice = LST_EQUIPO.indexOfFirst { it.id == equipo.id }
            LST_EQUIPO[indice] = equipo
        }

    }

}