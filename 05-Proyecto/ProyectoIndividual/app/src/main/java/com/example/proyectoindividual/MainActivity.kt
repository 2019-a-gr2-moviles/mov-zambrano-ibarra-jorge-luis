package com.example.proyectoindividual

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoindividual.modulo_clientes.MenuClientes
import com.example.proyectoindividual.modulo_reservas.MenuReservas
import com.example.proyectoindividual.modulo_canchas.MenuCancha
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_cancha.setOnClickListener {
            irMenuZapatos()
        }

        btn_clientes.setOnClickListener {
            irMenuClientes()
        }
        btn_reservas.setOnClickListener {
            irMenuCompras()
        }
    }

    fun irMenuZapatos() {
        val intent = Intent(
            this,
            MenuCancha::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    fun irMenuClientes() {
        val intent = Intent(
            this,
            MenuClientes::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irMenuCompras() {
        val intent = Intent(
            this,
            MenuReservas::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
