package com.example.parcelable

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intent_respuesta.*
import kotlinx.android.synthetic.main.activity_resultado.*

class Resultado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        btnResultado.setOnClickListener(){
            devolverRespuesta()
        }
        btn_respuseta_propia.setOnClickListener(){
            enviarIntentConRespuestaPropia()
        }
    }

    fun enviarIntentConRespuestaPropia(){
        val intentPropio=Intent(this, Resultado::class.java)
        this.startActivityForResult(intentPropio,305)

    }
    fun devolverRespuesta(){
        val nombre="JORGE"
        val edad= 25

        val intentRespuesta= Intent()

        intentRespuesta.putExtra("nombreUsuario", nombre)
        intentRespuesta.putExtra("edadUsuario", edad)
        this.setResult(
            RESULT_OK,
            intentRespuesta
        )
        this.finish()
    }
}
