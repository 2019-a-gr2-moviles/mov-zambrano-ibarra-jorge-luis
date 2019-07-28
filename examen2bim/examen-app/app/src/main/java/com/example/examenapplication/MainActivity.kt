package com.example.examenapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        JugadorHTTP().obtenerTodos()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EquipoHttp().obtenerTodos()

        button_crear_equi.setOnClickListener {
            this.irACrearEstudiante()
        }
        button_listar_equipo.setOnClickListener {
            this.irAListarEstudiante()
        }
        map.setOnClickListener {
            this.irAMapa()
        }

    }

    fun irACrearEstudiante(){
        intent = Intent(this, FormularioEquipoActivity::class.java)
        this.startActivity(intent)
    }

    fun irAListarEstudiante(){
        intent = Intent(this, ListarEquipoActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }


    fun irAMapa(){
        intent = Intent(this, MapActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }


    override fun onBackPressed() {
         intent = Intent(this, Login::class.java)

        startActivity(intent)
    }

}
