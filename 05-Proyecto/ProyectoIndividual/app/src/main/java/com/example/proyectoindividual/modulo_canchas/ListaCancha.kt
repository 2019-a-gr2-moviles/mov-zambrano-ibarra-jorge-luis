package com.example.proyectoindividual.modulo_canchas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.proyectoindividual.R
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_cancha.*

class ListaCancha : AppCompatActivity() {

    private val listaCanchas: ArrayList<Cancha> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cancha)
        obtenerCanchas()
    }

    fun iniciarRecyclerView(listaCanchas: ArrayList<Cancha>, actividad: ListaCancha, recyclerView: RecyclerView) {
        val adaptadorCancha =
            AdaptadorListaCancha(listaCanchas, actividad, recyclerView)
        rv_canchas.adapter = adaptadorCancha
        rv_canchas.itemAnimator = DefaultItemAnimator()
        rv_canchas.layoutManager = LinearLayoutManager(actividad)

        adaptadorCancha.notifyDataSetChanged()
    }

    fun obtenerCanchas() {
        //this.listaClientes.clear()

        val url = ("http://192.168.0.14:1337/cancha")
        Log.i("http", url)
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http error lista", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        val canchas = Klaxon()
                            .parseArray<Cancha>(data)

                        canchas?.forEach { cancha
                            ->
                            (
                                    this.listaCanchas.add(cancha)
                                    )
                        }
                        runOnUiThread {
                            iniciarRecyclerView(listaCanchas, this, rv_canchas)
                        }
                    }
                }
            }
    }

    fun irActulizarCancha(cancha: Cancha) {
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
    }

}
