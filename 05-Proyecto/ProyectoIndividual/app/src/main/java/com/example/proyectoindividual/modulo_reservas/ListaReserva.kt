package com.example.proyectoindividual.modulo_reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.proyectoindividual.Constantes
import com.example.proyectoindividual.R
import com.example.proyectoindividual.modulo_canchas.Cancha
import com.example.proyectoindividual.modulo_clientes.Cliente
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.layout_reserva.*
import kotlinx.android.synthetic.main.activity_lista_reserva.*
import java.lang.Exception

class ListaReserva : AppCompatActivity() {

    private val listaReservas: ArrayList<Reserva> = arrayListOf()
    private var listaClientes: ArrayList<Cliente>
    private var listaCanchas: ArrayList<Cancha>

    init {
        listaCanchas = arrayListOf<Cancha>()
        listaClientes = arrayListOf<Cliente>()

        obtenerCancha()
        obtenerClientes()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reserva)
        obtenerReservas()
    }

    fun iniciarRecyclerView(listaReservas: ArrayList<Reserva>, actividad: ListaReserva, recyclerView: RecyclerView) {
        val adaptadorReservas =
            AdaptadorListaReserva(listaReservas, actividad, recyclerView)
        rv_reservas.adapter = adaptadorReservas
        rv_reservas.itemAnimator = DefaultItemAnimator()
        rv_reservas.layoutManager = LinearLayoutManager(actividad)

        adaptadorReservas.notifyDataSetChanged()
    }

    fun obtenerReservas() {

        val url = "http://192.168.0.14:1337/reserva"
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

                        val reservas = Klaxon()
                            .parseArray<Reserva>(data)

                        reservas?.forEach { reserva ->
                            (
                                    this.listaReservas.add(reserva)
                                    )
                        }

                        runOnUiThread {
                            iniciarRecyclerView(listaReservas, this, rv_reservas)
                        }
                    }
                }
            }
    }
    fun eliminarReserva(reserva: Reserva) {
        val url = "http://192.168.0.14:1337/reserva/${reserva.id}"

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irListaReserva()
                        }
                    }
                }
            }
    }
    fun irActualizarReserva(reserva: Reserva) {


        obtenerCancha()
        obtenerClientes()

        val cancha = listaCanchas.find { cancha ->
            cancha.numero == txt_can_res.text.toString().toInt()

        }

        val cliente = listaClientes.find { cliente ->
            cliente.cedula == txt_cli_res.text.toString()
        }

        intent = Intent(
            this,
            ActualizarReserva::class.java
        )
        intent.putExtra("reserva-fecha", reserva.fecha)
        intent.putExtra("reserva-cancha",reserva.codigoCancha!!.numero)
        intent.putExtra("reserva-cliente",reserva.codigoCli!!.cedula)
        intent.putExtra("reserva-horai",reserva.horaInicial)
        intent.putExtra("reserva-horaf",reserva.horaFinal)
        intent.putExtra("reserva-id", reserva.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irListaReserva() {
        intent = Intent(
            this,
            ListaReserva::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun obtenerCancha() {
        val url = ("http://192.168.0.14:1337/cancha")
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
//                        Log.i("http", "Data: ${data}")

                        val canchas = Klaxon()
                            .parseArray<Cancha>(data)

                        canchas?.forEach { cancha ->
                            (
                                    this.listaCanchas.add(cancha)
                                    )
                        }
                    }
                }
            }
    }

    fun obtenerClientes() {
        //this.listaClientes.clear()
        try {
            val url = (Constantes.ip + Constantes.cliente)
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
//                            Log.i("http", "Data: ${data}")

                            val clientes = Klaxon()
                                .parseArray<Cliente>(data)

                            clientes?.forEach { cliente ->
                                (
                                        this.listaClientes.add(cliente)
                                        )
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("http", "${e}")
        }
    }
}
