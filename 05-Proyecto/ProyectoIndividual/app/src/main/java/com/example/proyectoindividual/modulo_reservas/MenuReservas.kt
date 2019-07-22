package com.example.proyectoindividual.modulo_reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoindividual.R
import kotlinx.android.synthetic.main.activity_menu_reserva.*

class MenuReservas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_reserva)

        btn_ing_rs.setOnClickListener {
            CrearReserva()
        }
        btn_ver_res.setOnClickListener {
            ListaReserva()
        }
        btn_mod_res.setOnClickListener {
            ListaReserva()
        }
    }

    fun CrearReserva() {
        intent = Intent(
            this,
            CrearReserva::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun ListaReserva() {
        intent = Intent(
            this,
            ListaReserva::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
