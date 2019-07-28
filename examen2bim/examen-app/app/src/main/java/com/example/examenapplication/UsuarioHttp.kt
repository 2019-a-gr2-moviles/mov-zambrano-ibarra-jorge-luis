package com.example.examenapplication

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class UsuarioHttp(var nombre: String,
                  var clave: String,
                    var createdAt: Long? = null,
                    var updatedAt: Long? = null,
                    var id: Int? = null) {
    constructor(): this ("", "", 0, 0, 0)

    val url = "http://192.168.0.14:1337/user"

    fun obtenerUsuarios() {
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpres11111", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("httpres", "${data}")

                        val equipoArray = Klaxon().parseArray<UsuarioHttp>(data)
                        Log.i("httpres11", "Datos: ${equipoArray?.toString()}")
                        if (equipoArray != null) {
                            BDD.equipo.clear()
                            for (equipo in equipoArray.iterator()) {


                                val equipoInsert = UsuarioHttp(
                                    equipo.nombre, equipo.clave,
                                    equipo.createdAt, equipo.updatedAt, equipo.id
                                )
                                BDD.usuario.add(equipoInsert)
                            }
                        }

                    }
                }

            }
    }}