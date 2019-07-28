package com.example.examenapplication
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog

import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_formulario_equipo.*
import kotlinx.android.synthetic.main.activity_formulario_jugador.*
import kotlinx.android.synthetic.main.activity_formulario_jugador.btnElim
import java.util.*

class FormularioJugadorActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_jugador)
        val equipoAActualizar = intent.getParcelableExtra<Jugador?>("materia_pasar")

        val equipo = intent.getParcelableExtra<Equipo?>("equipo")
        UsuarioHttp().obtenerUsuarios()

        if (equipoAActualizar != null) {
            Log.i("intent-editar", equipoAActualizar.nombreCompletoJugador)
            Log.i("intent-editar", "${equipoAActualizar.poderEspecialDos}")
            mostrarDatos(equipoAActualizar)
        }
        button_cancelar.setOnClickListener{
            JugadorHTTP().obtenerPorId(equipo!!.id)
            regresar(equipo)
        }
        button_guardarm.setOnClickListener {
            val materiaFormulario = obtenerParametros()


            if (equipoAActualizar == null) {
                materiaFormulario.crearJugador(equipo?.id)
                Alerter.create(this@FormularioJugadorActivity)
                    .setTitle(getString(R.string.msg)+" ${materiaFormulario.nombreCompletoJugador}")
                    .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                    .setDuration(10)
                    .setOnHideListener({

                        regresar(equipo)
                    })
                    .show()
            } else if (equipoAActualizar != null) {


                materiaFormulario.actualizar(equipoAActualizar.id)
                Alerter.create(this@FormularioJugadorActivity)
                    .setTitle(getString(R.string.msgActualizar)+" ${materiaFormulario.nombreCompletoJugador}")
                    .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                    .setDuration(1000)
                    .setOnHideListener {

                        regresar(equipo)
                    }
                    .show()


            }


        }

        btnElim.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder
                .setMessage(getString(R.string.msjEliminar))
                .setPositiveButton(
                    R.string.Si
                ) { dialog, which ->
                    JugadorHTTP().eliminar(equipoAActualizar!!.id)
                    Alerter.create(this)

                        .setTitle(getString(R.string.msgEliminar)+" ${equipoAActualizar.nombreCompletoJugador}")
                        .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                        .setDuration(100)
                        .setOnHideListener {

                            regresar(equipo)
                        }
                        .show()



                }
                .setNegativeButton(
                    "No"
                ) { dialog, which ->
                    Alerter.create(this)
                        .setText("NO")
                        .show()
                }


            val dialogo = builder.create()
            dialogo.show()




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
    override fun onBackPressed() {
        button_cancelar.callOnClick()
    }

}