package com.example.examenapplication

import android.content.Intent

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
class EquipoHttp(
    var nombre: String,
    var liga: String,
    var fechaCreacion: String,
    var numeroCopasInternacionales: Int,
    var campeonActual: Boolean,
    var createdAt: Long? = null,
    var updatedAt: Long? = null,
    var id: Int? = null
) : AppCompatActivity(){

    constructor(): this ("", "", "", 0, false,0)



    val url = "http://192.168.0.14:1337/equipo"

    fun crearEquipo() {

        val parametros = listOf(
            "nombre" to nombre,
            "liga" to liga,
            "fechaCreacion" to fechaCreacion,
            "numeroCopasInternacionales" to numeroCopasInternacionales,
            "campeonActual" to campeonActual

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

                        val equipoClase: EquipoHttp? = Klaxon()
                            .parse<EquipoHttp>(usuarioString)



                    }
                }
            }
    }

    fun obtenerTodos(){
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpres", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("httpres", "${data}")

                        val equipoArray = Klaxon().parseArray<EquipoHttp>(data)
                        Log.i("httpres", "Datos: ${equipoArray?.toString()}")
                        if (equipoArray != null) {
                            BDD.equipo.clear()
                            for ( equipo in equipoArray.iterator()){


                                val equipoInsert = EquipoHttp(equipo.nombre, equipo.liga,
                                    equipo.fechaCreacion, equipo.numeroCopasInternacionales, equipo.campeonActual,
                                    equipo.createdAt, equipo.updatedAt, equipo.id)
                                BDD.equipo.add(equipoInsert)
                            }
                        }

                    }
                }
            }
    }
    fun obtenerPorId(id: Int?){
        var urlParam = url+'/'+id
        urlParam.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpres", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("httpres", "${data}")

                        val equipoArray = Klaxon().parseArray<EquipoHttp>(data)
                        Log.i("httpres", "Datos: ${equipoArray?.toString()}")
                        if (equipoArray != null) {
                            BDD.equipo.clear()
                            for ( equipo in equipoArray.iterator()){


                                val equipoInsert = EquipoHttp(equipo.nombre, equipo.liga,
                                    equipo.fechaCreacion, equipo.numeroCopasInternacionales, equipo.campeonActual,
                                    equipo.createdAt, equipo.updatedAt, equipo.id)
                                BDD.equipo.add(equipoInsert)
                            }
                        }

                    }
                }
            }
    }
    fun eliminar(id:Int?){
        var urlParam = url+'/'+id
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

                        val equipoClase: EquipoHttp? = Klaxon()
                            .parse<EquipoHttp>(usuarioString)



                    }
                }
            }
    }

    fun actualizar(id:Int?){
        val urlParam = url+'/'+id

        val parametros = listOf(
            "nombre" to nombre,
            "liga" to liga,
            "fechaCreacion" to fechaCreacion,
            "numeroCopasInternacionales" to numeroCopasInternacionales,
            "campeonActual" to campeonActual



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

                        val equipoClase: EquipoHttp? = Klaxon()
                            .parse<EquipoHttp>(usuarioString)
                        obtenerTodos()

                    }
                }
            }
    }

    fun irAListarEquipo(){
        val myactivity = Intent(this.getApplicationContext(), PersonasAdaptador::class.java)
        myactivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.getApplicationContext().startActivity(myactivity)
    }
}