package com.example.proyectoindividual.modulo_clientes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoindividual.R
import kotlinx.android.synthetic.main.activity_menu_clientes.*

class MenuClientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_clientes)
        btn_ver_cli.setOnClickListener {
            ListaClientes()
        }

        btn_ing_cli.setOnClickListener {
            CrearCliente()
        }

        btn_mod_cli.setOnClickListener {
            ListaClientes()
        }
    }

    fun ListaClientes() {
        intent = Intent(
            this,
            ListaClientes::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun CrearCliente() {
        intent = Intent(
            this,
            CrearCliente::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
