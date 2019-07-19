package com.example.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragmentos.*

class FragmentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)
        btnPrimer.setOnClickListener {
            abrirPrimerFragmento()
        }
        btnSegundo.setOnClickListener {
            abrirSegundoFragmento()
        }

        btnTercero.setOnClickListener {
            abrirTercerFragmento()
        }
    }
    fun abrirPrimerFragmento(){
        val fragmentManager = supportFragmentManager
        val transaccion= fragmentManager.beginTransaction()
        val primerFragmento= Fragmento1()
        transaccion.replace(R.id.clyFragmentos,primerFragmento)
        transaccion.commit()
    }

    fun abrirSegundoFragmento(){
        val fragmentManager = supportFragmentManager
        val transaccion= fragmentManager.beginTransaction()
        val segundoFragmento= Fragmento2()
        transaccion.replace(R.id.clyFragmentos,segundoFragmento)
        transaccion.commit()
    }


    fun abrirTercerFragmento(){
        val fragmentManager = supportFragmentManager
        val transaccion= fragmentManager.beginTransaction()
        val tercerFragmento= Fragment3()

        val argumentos= Bundle()

        argumentos.putInt("contador",1)

        tercerFragmento.arguments=argumentos


        transaccion.replace(R.id.clyFragmentos,tercerFragmento)
        transaccion.commit()
    }
}
