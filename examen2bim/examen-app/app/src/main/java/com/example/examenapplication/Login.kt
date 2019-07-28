package com.example.examenapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_login2.*

class Login : AppCompatActivity() {
    private val usuario:Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)


        UsuarioHttp().obtenerUsuarios()
        btnIngresar.setOnClickListener {

            validar()
        }
    }


    fun validar() {
      val usuariofil= BDD.usuario.filter {
          it.nombre==user.text.toString()
      }

        if(usuariofil.isEmpty()){
            Alerter.create(this@Login)
                    .setTitle(getString(R.string.msjCredenciales)+" ${user.text.toString()}")

                    .show()
        }else if(!usuariofil[0].clave.equals(pass.text.toString())){
            Alerter.create(this@Login)
                    .setTitle(getString(R.string.msjCredenciales)+" ${user.text.toString()}")

                    .show()
        }else{
            irAListarEquipo()
        }

    }

    fun irAListarEquipo(){
        val myactivity = Intent(this, MainActivity::class.java)
        myactivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.getApplicationContext().startActivity(myactivity)
    }
    fun inicializar(){
        UsuarioHttp().obtenerUsuarios()
    }

    override fun onBackPressed() {

         intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
