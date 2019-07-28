package com.example.examenapplication
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog

import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_formulario_jugador.*
import java.util.*

class DetalleEquipo : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_equipo)
        val equipoAActualizar = intent.getParcelableExtra<Jugador?>("materia_pasar")

        //val equipo = intent.getParcelableExtra<Equipo?>("equipo")
        UsuarioHttp().obtenerUsuarios()

        if (equipoAActualizar != null) {
            Log.i("intent-editar", equipoAActualizar.nombreCompletoJugador)
            Log.i("intent-editar", "${equipoAActualizar.poderEspecialDos}")
            mostrarDatos(equipoAActualizar)
        }
        button_cancelar.setOnClickListener{
            irAMapa()
        }





    }


    fun regresar(equipo: Equipo?){
        JugadorHTTP().obtenerPorId(equipo!!.id)
        val intent = Intent(this, ListarJugadorActivity::class.java)
        intent.putExtra("equipo_pasar", equipo)
        this.startActivity(intent)
        this.finish()
    }

    fun obtenerParametros(): JugadorHTTP {

        val nombreCamiseta = txt_nomcam.text.toString()
        val poderEsp = txt_poderEsp.text.toString()
        val fechIng = txt_fech.text.toString()
        val numeroCamiseta = txt_numcam.text.toString().toInt()
        val nombreCompleto = txt_nombCom.text.toString()
        val goles = txt_goles.text.toString().toInt()
        val lat= txtLat.text.toString().toDouble()
        val long= txtLong.text.toString().toDouble()
        return JugadorHTTP(numeroCamiseta, nombreCamiseta, nombreCompleto, poderEsp, fechIng,goles, lat,long)
    }

    fun mostrarDatos(jugador: Jugador) {
        txt_numcam.setText(jugador.numeroCamiseta.toString())
        txt_nomcam.setText(jugador.nombreCamiseta)
        txt_fech.setText(jugador.fechaIngresoEquipo)
        txt_nombCom.setText(jugador.nombreCompletoJugador)
        txt_poderEsp.setText(jugador.poderEspecialDos)
        txt_goles.setText(jugador.goles.toString())
        txtLat.setText(jugador.latitud.toString())
        txtLong.setText(jugador.longitud.toString())

    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {


        }
    }






    companion object {
        val TOMAR_FOTO_REQUEST = 1
        var respuestasBarCode = ArrayList<String>()
    }

    fun irAMapa(){
        intent = Intent(this, MapActivity::class.java)
        this.startActivity(intent)
        this.finish()

    }

}