package com.example.proyectoindividual.modulo_canchas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectoindividual.R
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_cancha.*
import kotlinx.android.synthetic.main.activity_actualizar_cancha.txt_act_cli_res
import kotlinx.android.synthetic.main.activity_actualizar_cancha.txt_act_horaf_r
import kotlinx.android.synthetic.main.activity_actualizar_cancha.txt_act_num_res
import kotlinx.android.synthetic.main.activity_actualizar_cancha.txt_act_horai_reser

class ActualizarCancha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_cancha)

        val numeroCancha: Int? = this.intent.getIntExtra("cancha-numero",-1)
        val descripcionCancha: String? = this.intent.getStringExtra("cancha-desc")
        val precioCancha: Double? = this.intent.getDoubleExtra("cancha-precio",-1.0)
        val metrosCancha: Double? = this.intent.getDoubleExtra("cancha-metros", -1.0)
        val canchaId: Int? = this.intent.getIntExtra("cancha-id", -1)



        txt_act_id_can.text = canchaId.toString()
        txt_act_num_res.hint= numeroCancha.toString()
        txt_act_cli_res.hint= descripcionCancha
        txt_act_horai_reser.hint= precioCancha.toString()
        txt_act_horaf_r.hint= metrosCancha.toString()

        btn_act_can.setOnClickListener {
            val cancha = Cancha(
//                null,
                null,
                null,
                txt_act_id_can.text.toString().toInt(),
                txt_act_num_res.text.toString().toInt(),
                txt_act_cli_res.text.toString(),
                txt_act_horai_reser.text.toString().toDouble(),
                txt_act_horaf_r.text.toString().toDouble()
            )
            actualizarCancha(cancha)
        }
    }

    fun actualizarCancha(cancha: Cancha) {

        val url = "http://192.168.0.14:1337/cancha"+"/${cancha.id}"
        val json = """
            {
            "numero": "${cancha.numero}",
            "desc": "${cancha.desc}",
            "precio": "${cancha.precio}",
            "metros": ${cancha.metros}
                                   }
                    """

        url.httpPut().body(json)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "$response")
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
