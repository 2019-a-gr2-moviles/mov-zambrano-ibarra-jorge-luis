package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actividad_dos.*
import kotlinx.android.synthetic.main.content_main.*

class ActividadDos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_dos)
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
