package com.example.jorge.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingresar.*
import kotlinx.android.synthetic.main.activity_ingresar.txtNombre
import kotlinx.android.synthetic.main.activity_main.*

class IngresarActivity : AppCompatActivity() {
    var usuario:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)
        usuario = intent.getStringExtra("usuario").toString()
        btnAceptar.setOnClickListener { aceptarIngreso() }
        btnCancelar.setOnClickListener { cancelarIngreso()}
    }

    fun cancelarIngreso(){
        val intentCancelar= Intent(this, MenuActivity::class.java)
        intentCancelar.putExtra("usuario", usuario)
        startActivity(intentCancelar)
    }

    fun aceptarIngreso(){
        val camp: Byte
        if(checkBox.isChecked){
            camp=1
        }else{
            camp=0
        }
        val equipo= EquipoFutbol(
            id = null,
            nombre = txtNombre.text.toString(),
            liga = txtLiga.text.toString(),
            fechaCreacion = dateFechCreac.text.toString(),
            numeroCopasInternacionales = txtNumCopInter.text.toString().toInt(),
            campeonActual = camp
        )
        BDEquipoFutbol.agregarEquipo(equipo)
        Toast.makeText(this, getString(R.string.msg)+" " +usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }
    override fun onBackPressed() {

        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
