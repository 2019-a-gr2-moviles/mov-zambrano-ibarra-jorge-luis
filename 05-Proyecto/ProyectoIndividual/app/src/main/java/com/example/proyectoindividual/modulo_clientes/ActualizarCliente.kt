package com.example.proyectoindividual.modulo_clientes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectoindividual.R
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_cliente.*
import kotlinx.android.synthetic.main.activity_actualizar_cliente.txt_acc_ced_cli
import kotlinx.android.synthetic.main.activity_actualizar_cliente.txt_acc_dir_cli
import kotlinx.android.synthetic.main.activity_actualizar_cliente.txt_acc_nom_cli
import kotlinx.android.synthetic.main.activity_actualizar_cliente.txt_acc_tel_cli

class ActualizarCliente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_cliente)

        val clienteNombre: String? = this.intent.getStringExtra("cliente-nombre")
        val clienteTelefono: String? = this.intent.getStringExtra("cliente-telefono")
        val clienteDirrecion: String? = this.intent.getStringExtra("cliente-direccion")
        val clienteCedula: String? = this.intent.getStringExtra("cliente-cedula")
        val clienteId: Int? = this.intent.getIntExtra("cliente-id", -1)


        txt_act_id_cli.text= clienteId.toString()
        txt_acc_nom_cli.hint= clienteNombre
        txt_acc_ced_cli.hint=clienteCedula
        txt_acc_tel_cli.hint=clienteTelefono
        txt_acc_dir_cli.hint= clienteDirrecion


        btn_acc_act_cli.setOnClickListener {
            val cliente =
                Cliente(
//                    null,
                    null,
                    null,
                    txt_act_id_cli.text.toString().toInt(),
                    txt_acc_nom_cli.text.toString(),
                    txt_acc_ced_cli.text.toString(),
                    txt_acc_tel_cli.text.toString(),
                    txt_acc_dir_cli.text.toString()

                )
            actualizarCliente(cliente)

        }

    }




    fun actualizarCliente(cliente: Cliente) {
        val url = "http://192.168.0.14:1337/cliente"+ "/${cliente.id}"
        val json = """
            {
            "cedula": "${cliente.cedula}",
            "nombre": "${cliente.nombre}",
            "telefono": "${cliente.telefono}",
            "direccion": "${cliente.direccion}"
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

                        runOnUiThread {
                            irListaClientes()
                        }
                    }
                }
            }
    }

    fun irListaClientes() {
        intent = Intent(
            this,
            ListaClientes::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
