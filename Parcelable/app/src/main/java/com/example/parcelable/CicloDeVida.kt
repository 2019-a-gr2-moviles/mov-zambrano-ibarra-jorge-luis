package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_de_vida.*
import kotlinx.android.synthetic.main.activity_main.*

class CicloDeVida : AppCompatActivity() {

    var contador = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_de_vida)
        Log.i("ciclo vida", "onCreate")
        btnContador.setOnClickListener {
            aumentarContador()
        }
    }

    fun aumentarContador() {
        contador++
        txtContador.text = contador.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo vida", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo vida", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo vida", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo vida", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo vida", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.i("ciclo vida", "onSaveInstanceState")
        outState?.run{
            putInt("contadorGuardado", contador)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("ciclo vida", "onRestoreInstanceState")
        val contadorRecuperado =savedInstanceState?.getInt("contadorGuardado")

        if(contadorRecuperado!=null){
            this.contador = contadorRecuperado;
            txtContador.text=this.contador.toString()
        }
    }
}
