package com.example.proyectoindividual.modulo_canchas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectoindividual.R
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_cancha.*

class CrearCancha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cancha)
        btn_ing_can.setOnClickListener {
            val cancha = Cancha(
                null,
                null,
                null,
//                null,
                txt_act_num_res.text.toString().toInt(),
                txt_act_cli_res.text.toString(),
                txt_act_horai_reser.text.toString().toDouble(),
                txt_act_horaf_r.text.toString().toDouble()

            )
            ingresarCancha(cancha)
        }
    }

    fun ingresarCancha(cancha: Cancha) {

        val url = "http://192.168.0.14:1337/cancha"
        val json = """
            {
            "numero": "${cancha.numero}",
            "desc": "${cancha.desc}",
            "precio": "${cancha.precio}",
            "metros": ${cancha.metros}
            }
                    """
        Log.i("http-json", json)
        url.httpPost().body(json)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irListaCancha()
                        }
                    }
                }
            }
    }

    fun irListaCancha() {
        intent = Intent(
            this,
            ListaCancha::class.java
        )
        startActivity(intent)
    }
}
