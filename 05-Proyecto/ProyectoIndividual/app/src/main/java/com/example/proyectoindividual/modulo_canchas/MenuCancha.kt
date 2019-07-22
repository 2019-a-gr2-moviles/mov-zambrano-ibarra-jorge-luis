package com.example.proyectoindividual.modulo_canchas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoindividual.R
import kotlinx.android.synthetic.main.activity_menu_cancha.*

class MenuCancha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_cancha)
        btn_ver_can.setOnClickListener {
            irListaCancha()
        }

        btn_ing_can.setOnClickListener {
           irCrearCancha()
        }
        btn_mod_can.setOnClickListener {
            irListaCancha()
        }
        btn_ver_can.setOnClickListener {
            irListaCancha()
        }
    }

    fun irListaCancha() {
        intent = Intent(
            this,
            ListaCancha::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irCrearCancha() {
        intent = Intent(
            this,
            CrearCancha::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
