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
}
