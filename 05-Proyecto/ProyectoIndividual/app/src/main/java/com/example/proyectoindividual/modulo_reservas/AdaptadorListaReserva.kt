package com.example.proyectoindividual.modulo_reservas

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.beust.klaxon.Klaxon
import com.example.proyectoindividual.Constantes
import com.example.proyectoindividual.R
import com.example.proyectoindividual.clases_auxiliares.CanchaAux
import com.example.proyectoindividual.clases_auxiliares.ClienteAux
import com.example.proyectoindividual.modulo_canchas.Cancha
import com.example.proyectoindividual.modulo_clientes.Cliente
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import java.lang.Exception

class AdaptadorListaReserva(
    private val listaReservas: ArrayList<Reserva>,
    private val contexto: ListaReserva,
    private val recyclerView: RecyclerView

) :

    RecyclerView.Adapter<AdaptadorListaReserva.MyViewHolder>() {
    private lateinit var listaClientes: ArrayList<Cliente>
    private lateinit var listaCanchas: ArrayList<Cancha>

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView
        var feechaTextView: TextView
        var clienteTextView: TextView
        var canchaTextView: TextView
        var horaiTextVew: TextView
        var totalTextView: TextView
        var horafTextView: TextView
        var precioUnitarioTextView: TextView
        var horasTextView: TextView

        var anularBoton: Button


        init {

            listaCanchas = arrayListOf<Cancha>()
            listaClientes = arrayListOf<Cliente>()

            obtenerCancha()
            obtenerClientes()
            idTextView = view.findViewById(R.id.txt_id_res) as TextView
            feechaTextView = view.findViewById(R.id.txt_fech_res) as TextView
            clienteTextView = view.findViewById(R.id.txt_cli_res) as TextView
            canchaTextView = view.findViewById(R.id.txt_can_res) as TextView
            horaiTextVew = view.findViewById(R.id.txt_horai_res) as TextView
            totalTextView = view.findViewById(R.id.txt_tot_com) as TextView
            horafTextView = view.findViewById(R.id.txt_horaf_res) as TextView
            precioUnitarioTextView= view.findViewById(R.id.txt_pre_res) as TextView
            horasTextView=view.findViewById(R.id.txt_hor_res) as TextView
            anularBoton = view.findViewById(R.id.btn_anu_com) as Button
            val cancha = listaCanchas.find { cancha ->
                cancha.numero == canchaTextView.text.toString().toInt()
            }

            val cliente = listaClientes.find { cliente ->
                cliente.cedula == clienteTextView.text.toString()
            }
            val layout = view.findViewById(R.id.lay_cam_res) as LinearLayout
            layout.setOnClickListener {
                val reserva = Reserva(

                    idTextView.text.toString().toInt(),
                    feechaTextView.text.toString(),
                    horaiTextVew.text.toString().toInt(),
                    horafTextView.text.toString().toInt(),
                    ClienteAux(
                        cliente?.id,
                        "",
                        clienteTextView.text.toString(),
                        "",
                        ""
                    ),
                    CanchaAux(
                        cancha?.id,
                        canchaTextView.text.toString().toInt(),
                        "",
                        0.0,
                        0.0
                    )


                )
                contexto.irActualizarReserva(reserva)

            }

            anularBoton.setOnClickListener {

                val reserva = Reserva(

                    idTextView.text.toString().toInt(),
                    feechaTextView.text.toString(),
                    horaiTextVew.text.toString().toInt(),
                    horafTextView.text.toString().toInt(),
                    ClienteAux(cliente?.id, "", "", "", ""),
                    CanchaAux(cancha?.id, 0, "", 0.0, 0.0)


                )


                contexto.eliminarReserva(reserva)

            }


        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_reserva,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaReservas.size
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val reserva: Reserva = listaReservas[position]
        myViewHolder.idTextView.text = reserva.id.toString()
        myViewHolder.feechaTextView.text = reserva.fecha
        myViewHolder.clienteTextView.text = reserva.codigoCli!!.cedula
        myViewHolder.canchaTextView.text = reserva.codigoCancha!!.numero.toString()

        myViewHolder.horafTextView.text = reserva.horaFinal.toString()
        myViewHolder.horaiTextVew.text = reserva.horaInicial.toString()
        var horas = reserva.horaFinal-reserva.horaInicial
        myViewHolder.horasTextView.text = horas.toString()
        myViewHolder.precioUnitarioTextView.text = reserva.codigoCancha!!.precio.toString()
        var total= horas*reserva.codigoCancha!!.precio

        myViewHolder.totalTextView.text = total.toString()





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
