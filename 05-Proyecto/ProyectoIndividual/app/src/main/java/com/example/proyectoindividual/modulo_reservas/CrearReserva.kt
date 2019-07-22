package com.example.proyectoindividual.modulo_reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.example.proyectoindividual.Constantes
import com.example.proyectoindividual.R
import com.example.proyectoindividual.clases_auxiliares.ClienteAux
import com.example.proyectoindividual.clases_auxiliares.CanchaAux
import com.example.proyectoindividual.modulo_clientes.Cliente
import com.example.proyectoindividual.modulo_canchas.Cancha
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_reserva.*
import java.lang.Exception


class CrearReserva : AppCompatActivity() {
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
        setContentView(R.layout.activity_crear_reserva)

        btn_ing_can.setOnClickListener {
            try {

                val cancha = listaCanchas.find { cancha ->
                    cancha.numero == txt_act_num_res.text.toString().toInt()
                }

                val cliente = listaClientes.find { cliente ->
                    cliente.cedula == txt_act_cli_res.text.toString()
                }


                if (cancha != null && cliente != null) {
                    val reserva = Reserva(
                        null,
                        fechaReserva.text.toString(),
                        txt_act_horai_reser.text.toString().toInt(),

                        txt_act_horaf_r.text.toString().toInt(),
                        ClienteAux(cliente.id, "", "", "", ""),
                        CanchaAux(cancha.id, 0, "", 0.0, 0.0)
                    )

                    ingresarReserva(reserva)
                    Toast.makeText(this, "Reserva ingresada correctamente", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "Cédula o numero de cancha no valido", Toast.LENGTH_LONG).show();
                }
//            }
            } catch (e: Exception) {
                Toast.makeText(this, "Cédula o numero de cancha no valido", Toast.LENGTH_LONG).show();
            }
        }
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

    fun ingresarReserva(reserva: Reserva) {
        val url = "http://192.168.0.14:1337/reserva"
        val json = """
            {
            "fecha": "${reserva.fecha}",
            "horaInicial": ${reserva.horaInicial},
            "horaFinal": ${reserva.horaFinal},
            "codigoCli": ${reserva.codigoCli!!.id},
            "codigoCancha": ${reserva.codigoCancha!!.id}
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
                        runOnUiThread {
                            irListaReserva()
                        }
                    }
                }
            }
    }

    fun irListaReserva() {
        intent = Intent(
            this,
            ListaReserva::class.java
        )
        startActivity(intent)
    }
}
