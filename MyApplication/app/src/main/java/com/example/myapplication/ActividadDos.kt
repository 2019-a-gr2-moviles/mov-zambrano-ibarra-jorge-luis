package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_actividad_dos.*
import kotlinx.android.synthetic.main.content_main.*

class ActividadDos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_dos)
        val nombre: String?=intent.getStringExtra("NOMBRE")
        val edad:Int? =intent.getIntExtra("edad",0)

        Log.i("intents","nombre: $nombre")
        Log.i("intents","edad: $edad")

        btnActividadUno.setOnClickListener{
            irAActividadUno()
        }

    }

    fun irAActividadUno(){

        val intent= Intent(
            this,
            MainActivity::class.java
        )

        startActivity(intent)

    }
}
