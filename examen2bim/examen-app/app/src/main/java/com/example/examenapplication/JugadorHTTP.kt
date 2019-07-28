package com.example.examenapplication

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class JugadorHTTP(
    var numeroCamiseta:Int,
    var nombreCamiseta: String,
    var nombreCompletoJugador: String,
    var poderEspecialDos: String,
    var fechaIngresoEquipo: String,
    var goles: Int,
    var latitud: Double,
    var longitud: Double,
    var createdAt: Long? = null,
    var updatedAt: Long? = null,
    var id: Int? = null
) {
    constructor(): this (0, "", "", "", "", 0,0.0,0.0, 0, 0)


    val url = "http://192.168.0.14:1337/jugador"

    fun crearJugador(idEquipo: Int?) {

        val parametros = listOf(
            "numeroCamiseta" to numeroCamiseta,
            "nombreCamiseta" to nombreCamiseta,
            "nombreCompletoJugador" to nombreCompletoJugador,
            "poderEspecialDos" to poderEspecialDos,
            "fechaIngresoEquipo" to fechaIngresoEquipo,
            "goles" to goles,
            "latitud"  to latitud,
            "longitud" to longitud,
            "equipoFutbolId" to idEquipo
        )

        Log.i("httpres", parametros.toString())

        url
            .httpPost(parametros)
            .responseString { request, response, result ->

                when (result) {
                    is Result.Failure -> {
                        val exepcion = result.getException()
                        Log.i("httpres", "Error: ${exepcion}")
                        Log.i("httpres", "Error: ${response}")

                    }
                    is Result.Success -> {

                        val usuarioString = result.get()

                        val jugadorClase: JugadorHTTP? = Klaxon()
                            .parse<JugadorHTTP>(usuarioString)

                        Log.i("httpres", "Datos: ${jugadorClase?.nombreCompletoJugador}")

                    }
                }
            }
    }



    fun eliminar(id:Int?){
        val urlParam = url+'/'+id
        urlParam.httpDelete()
            .responseString { request, response, result ->

                when (result) {
                    is Result.Failure -> {
                        val exepcion = result.getException()
                        Log.i("httpres", "Error: ${exepcion}")
                        Log.i("httpres", "Error: ${response}")

                    }
                    is Result.Success -> {

                        val usuarioString = result.get()

                        val jugadorClase: JugadorHTTP? = Klaxon()
                            .parse<JugadorHTTP>(usuarioString)

                        Log.i("httpres", "Datos: ${jugadorClase?.nombreCompletoJugador}")

                    }
                }
            }
    }


    fun obtenerTodos() {
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpres", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("data", "Data: ${data}")

                        val materiaArray = Klaxon().parseArray<JugadorHTTP>(data)
                        Log.i("httpres111", "Datos: ${materiaArray?.toString()}")
                        if (materiaArray != null) {
                            BDD.jugador1.clear()
                            for ( materia in materiaArray.iterator()){
                                Log.i("httpres", "Equipo: ${materia.nombreCompletoJugador}")
                                Log.i("httpres", "Equipo: ${materia.id}")

                                val materiaInsert = JugadorHTTP(materia.numeroCamiseta, materia.nombreCamiseta, materia.nombreCompletoJugador,
                                    materia.poderEspecialDos, materia.fechaIngresoEquipo, materia.goles, materia.latitud,materia.longitud,
                                    materia.createdAt, materia.updatedAt, materia.id)
                                BDD.jugador1.add(materiaInsert)
                            }
                        }

                    }
                }
            }
    }

    fun obtenerPorId(idEquipo: Int?){
//        val urlParam = url+'/'+id
        Log.i("idEqui", "${idEquipo}")
        val parametros = listOf(
            "equipoFutbolId" to idEquipo

        )
        url.httpGet(parametros)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpres", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("data", "Data: ${data}")

                        val materiaArray = Klaxon().parseArray<JugadorHTTP>(data)
                        Log.i("httpres111", "Datos: ${materiaArray?.toString()}")
                        if (materiaArray != null) {
                            BDD.jugador.clear()
                            for ( materia in materiaArray.iterator()){
                                Log.i("httpres", "Equipo: ${materia.nombreCompletoJugador}")
                                Log.i("httpres", "Equipo: ${materia.id}")

                                val materiaInsert = JugadorHTTP(materia.numeroCamiseta, materia.nombreCamiseta, materia.nombreCompletoJugador,
                                    materia.poderEspecialDos, materia.fechaIngresoEquipo, materia.goles, materia.latitud,materia.longitud,
                                    materia.createdAt, materia.updatedAt, materia.id)
                                BDD.jugador.add(materiaInsert)
                            }
                        }

                    }
                }
            }
    }

    fun actualizar(id:Int?){
        val urlParam = url+'/'+id

        val parametros = listOf(
            "numeroCamiseta" to numeroCamiseta,
            "nombreCamiseta" to nombreCamiseta,
            "nombreCompletoJugador" to nombreCompletoJugador,
            "poderEspecialDos" to poderEspecialDos,
            "fechaIngresoEquipo" to fechaIngresoEquipo,
            "goles" to goles,
            "latitud"  to latitud,
            "longitud" to longitud
//            "idEstudiante" to idEstudiante

        )

        urlParam.httpPut(parametros)
            .responseString { request, response, result ->

                when (result) {
                    is Result.Failure -> {
                        val exepcion = result.getException()
                        Log.i("httpres", "Error: ${exepcion}")
                        Log.i("httpres", "Error: ${response}")

                    }
                    is Result.Success -> {

                        val usuarioString = result.get()

                        val jugadorClase: JugadorHTTP? = Klaxon()
                            .parse<JugadorHTTP>(usuarioString)

                        Log.i("httpres", "Datos: ${jugadorClase?.nombreCompletoJugador}")

                    }
                }
            }
    }


}