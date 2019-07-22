package com.example.proyectoindividual.modulo_clientes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.aplicacion2.AdaptadorListaClientes
import com.example.proyectoindividual.Constantes
import com.example.proyectoindividual.R
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_clientes.*
import java.lang.Exception

class ListaClientes : AppCompatActivity() {
    private val listaClientes: ArrayList<Cliente> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_clientes)
        obtenerClientes()

    }

    fun iniciarRecyclerView(listaClientes: ArrayList<Cliente>, actividad: ListaClientes, recyclerView: RecyclerView) {
        val adaptadorCliente = AdaptadorListaClientes(listaClientes, actividad, recyclerView)
        rv_clientes.adapter = adaptadorCliente
        rv_clientes.itemAnimator = DefaultItemAnimator()
        rv_clientes.layoutManager = LinearLayoutManager(actividad)

        adaptadorCliente.notifyDataSetChanged()
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
                            Log.i("http", "Data: ${data}")

                            val clientes = Klaxon()
                                .parseArray<Cliente>(data)

                            clientes?.forEach { cliente ->
                                (
                                        this.listaClientes.add(cliente)
                                        )
                            }
                            runOnUiThread {
                                iniciarRecyclerView(listaClientes, this, rv_clientes)
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("http", "${e}")
        }
    }

    fun eliminarCliente(cliente: Cliente) {
        val url = "${Constantes.ip}${Constantes.cliente}/${cliente.id}"

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irListaClientes()
                        }
                    }
                }
            }
    }

    fun irListaClientes() {
        intent = Intent(
            this,
            ListaClientes::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irActulizarCliente(cliente: Cliente) {
        intent = Intent(
            this,
            ActualizarCliente::class.java
        )
        intent.putExtra("cliente-nombre", cliente.nombre)
        intent.putExtra("cliente-telefono", cliente.telefono)
        intent.putExtra("cliente-direccion", cliente.direccion)
        intent.putExtra("cliente-cedula", cliente.cedula)
        intent.putExtra("cliente-id", cliente.id as Int)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
