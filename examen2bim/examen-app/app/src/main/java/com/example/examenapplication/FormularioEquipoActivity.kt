package com.example.examenapplication

import android.content.Intent

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tapadoo.alerter.Alerter


import kotlinx.android.synthetic.main.activity_formulario_equipo.*
//import android.support.test.espresso.matcher.ViewMatchers.isChecked


class FormularioEquipoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_equipo)
        UsuarioHttp().obtenerUsuarios()
        val equipoAActualizar = intent.getParcelableExtra<Equipo?>("equipo_pasar")

        if (equipoAActualizar != null) {
            Log.i("intent-editar", equipoAActualizar.nombre)
            Log.i("intent-editar", "${equipoAActualizar.numeroCopasInternacionales}")
            mostrarDatos(equipoAActualizar)
        }

        intent = Intent(this, MainActivity::class.java)

        btn_equipo_canc.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }

        btnElim.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder
                .setMessage(getString(R.string.msjEliminar))
                .setPositiveButton(
                    R.string.Si
                ) { dialog, which ->
                    EquipoHttp().eliminar(equipoAActualizar!!.id)
                    Alerter.create(this)
                        .setTitle(getString(R.string.msgEliminar)+" ${equipoAActualizar!!.nombre}")
                        .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                        .setDuration(100)
                        .setOnHideListener {
                            JugadorHTTP().obtenerPorId(equipoAActualizar!!.id)
                            regresar(equipoAActualizar)
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


        btn_equipo_guardar.setOnClickListener {
            val equipoFormulario = obtenerParametros()


            if (equipoAActualizar == null) {
                equipoFormulario.crearEquipo()
                Alerter.create(this@FormularioEquipoActivity)
                    .setTitle(getString(R.string.msg)+" ${equipoFormulario.nombre}")
                    .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                    .setDuration(10000)
                    .setOnHideListener({
                        EquipoHttp().obtenerTodos()
                        irAListarEquipo()
                    })
                    .show()

            } else if (equipoAActualizar != null) {


                equipoFormulario.actualizar(equipoAActualizar.id)
                Alerter.create(this@FormularioEquipoActivity)
                    .setTitle(getString(R.string.msgActualizar)+" ${equipoFormulario.nombre}")
                    .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                    .setDuration(10000)
                    .setOnHideListener {
                        EquipoHttp().obtenerTodos()
                        irAListarEquipo()
                    }
                    .show()







            }


        }

    }

    fun devolverActualizar(equipoPorActualizar: Equipo, equipoActual: EquipoHttp) {

        equipoPorActualizar.nombre = equipoActual.nombre
        equipoPorActualizar.liga = equipoActual.liga
        equipoPorActualizar.fechaCreacion = equipoActual.fechaCreacion
        equipoPorActualizar.numeroCopasInternacionales = equipoActual.numeroCopasInternacionales
        equipoPorActualizar.campeonActual = equipoActual.campeonActual

        val intentRespuesta = Intent()

        Log.i("equipo_pasar", "${equipoPorActualizar.nombre}")
        intentRespuesta.putExtra("equipo_pasar", equipoPorActualizar)


        this.finish()

    }
    fun irAListarEquipo(){
        EquipoHttp().obtenerTodos()
        intent = Intent(this, ListarEquipoActivity::class.java)
        this.startActivity(intent)

    }
    fun regresar(equipo: Equipo?){
        val intent = Intent(this, ListarEquipoActivity::class.java)
        intent.putExtra("equipo_pasar", equipo)
        this.startActivity(intent)

    }

    fun obtenerParametros(): EquipoHttp {

        val nombres = txt_equipo_nombre.text.toString()
        val apellidos = txt_equipo_liga.text.toString()
        val fechaNacimiento = txt_equipo_fec.text.toString()
        val semestreActual = txt_equipo_copas.text.toString().toInt()
        val graduado = chk_equipo_camp.isChecked()

        return EquipoHttp(nombres, apellidos, fechaNacimiento, semestreActual, graduado)
    }

    fun mostrarDatos(equipo: Equipo) {
        txt_equipo_nombre.setText(equipo.nombre)
        txt_equipo_liga.setText(equipo.liga)
        txt_equipo_fec.setText(equipo.fechaCreacion)
        txt_equipo_copas.setText(equipo.numeroCopasInternacionales.toString())
        chk_equipo_camp.isChecked = equipo.campeonActual
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }

}