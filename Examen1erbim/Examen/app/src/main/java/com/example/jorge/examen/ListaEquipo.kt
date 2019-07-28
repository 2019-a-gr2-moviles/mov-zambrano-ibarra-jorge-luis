package com.example.jorge.examen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import java.lang.Exception

class ListaEquipo : AppCompatActivity() {

    private val ListaEquipo: ArrayList<EquipoFutbol> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_equipo)
        obtenerEquipos()
    }
    /*fun iniciarRecyclerView(listaEquipo: ArrayList<EquipoFutbol>, actividad: ListaEquipo, recyclerView: RecyclerView) {
        val adaptadorCancha =
            AdaptadorListaCancha(listaEquipo, actividad, recyclerView)
        rv_canchas.adapter = adaptadorCancha
        rv_canchas.itemAnimator = DefaultItemAnimator()
        rv_canchas.layoutManager = LinearLayoutManager(actividad)

        adaptadorCancha.notifyDataSetChanged()
    }*/


    fun obtenerEquipos() {
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
                                        ListaEquipo.add(equipo)
                                        )
                            }

                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("http", "${e}")
        }

    }

    /*fun irActulizarCancha(cancha: Cancha) {
        intent = Intent(
            this,
            ActualizarCancha::class.java
        )
        intent.putExtra("cancha-numero", cancha.numero)
        intent.putExtra("cancha-desc", cancha.desc)
        intent.putExtra("cancha-metros", cancha.metros)
        intent.putExtra("cancha-precio", cancha.precio)
        intent.putExtra("cancha-id", cancha.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun eliminarCancha(cancha: Cancha) {
        val url = "http://192.168.0.14:1337/cancha/${cancha.id}"

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http lista", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irListaCanchas()
                        }
                    }
                }
            }
    }

    fun irListaCanchas() {
        intent = Intent(
            this,
            ListaCancha::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }*/

}
