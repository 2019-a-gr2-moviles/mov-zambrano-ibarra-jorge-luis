package com.example.jorge.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar.*
import kotlinx.android.synthetic.main.activity_main.*

class ConsultarActivity : AppCompatActivity() {
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)
        usuario = intent.getStringExtra("usuario").toString()
        val adapter = ArrayAdapter<EquipoFutbol>(
            this,
            android.R.layout.simple_list_item_1,
            BDEquipoFutbol.mostrarEquipo()
        )
        lstView.adapter = adapter;
        lstView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val equipoSeleccionada = parent.getItemAtPosition(position) as EquipoFutbol
            val intentEquipoSeleccionada = Intent(this, ActualizarActivity::class.java)
            intentEquipoSeleccionada.putExtra("Equipo", equipoSeleccionada)
            intentEquipoSeleccionada.putExtra("usuario", usuario)
            startActivity(intentEquipoSeleccionada)
        }


    }
    override fun onBackPressed() {

        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
