

package com.example.examenapplication

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_listar_equipo.*

import android.widget.PopupMenu
import java.util.*
import com.tapadoo.alerter.Alerter
//import android.R.attr.data


//import android.R


class ListarEquipoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EquipoHttp().obtenerTodos()
        setContentView(R.layout.activity_listar_equipo)

        EquipoHttp().obtenerTodos()
        UsuarioHttp().obtenerUsuarios()
        for (equipo in BDD.equipo) {
            Log.i("bdd-", equipo.nombre)
        }

        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val rv = rview_equipo

        val adaptador = PersonasAdaptador(BDD.equipo, this)

        rview_equipo.layoutManager = layoutManager
        rview_equipo.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rview_equipo.adapter = adaptador

        adaptador.notifyDataSetChanged()

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("int", "$resultCode")


        when (resultCode) {
            AppCompatActivity.RESULT_OK -> {

                Log.i("int", "$requestCode")
                Log.i("int", "$resultCode")
                Log.i("int", "$data")


                Log.i("intent-nombreCompletoJugador-apellido", "LLEGOOOO ${data!!.getParcelableExtra<Equipo?>("equipo_pasar")}")

                val equipo = data!!.getParcelableExtra<Equipo?>("equipo_pasar")


                if (equipo != null) {

                    Log.i("as","asdfdsfasd")
                    actualizarEqu(equipo)
                    Alerter.create(this@ListarEquipoActivity)
                        .setTitle(getString(R.string.msgEliminar)+" ${equipo!!.nombre}")
                        .setText(getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                        .show()
                }




            }

            RESULT_CANCELED -> {
                Log.i("error", "Error")
            }
        }


    }

    fun actualizarEqu(equipo: Equipo) {
        val equipoAActualizar = EquipoHttp(
            equipo.nombre,
            equipo.liga,
            equipo.fechaCreacion,
            equipo.numeroCopasInternacionales,
            equipo.campeonActual
        )
        equipoAActualizar.actualizar(equipo.id)
    }

    companion object {
        val requestCodeActualizar = 101
    }
    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}


class PersonasAdaptador(var listaEquipos: ArrayList<EquipoHttp>, private val contexto: ListarEquipoActivity) :
    androidx.recyclerview.widget.RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {


    // val intentEditar = intent

    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        var nombreTextView: TextView
        var apellidoTextView: TextView
        //        var fechaNacimientoText
        var boton_opciones: Button

        init {
            EquipoHttp().obtenerTodos()
            nombreTextView = view.findViewById(R.id.textView_nombre) as TextView
            apellidoTextView = view.findViewById(R.id.textView_liga) as TextView
//            fechaNacimientoTextView = view.findViewById(R.id.textView_fechaNacimiento) as TextView

            boton_opciones = view.findViewById(R.id.button_opciones) as Button

            val layout = view.findViewById(R.id.relative_layout_equipo) as RelativeLayout

            /*
            layout
                .setOnClickListener {
                    val equipo = BDD.equipo[position]
                    val intentEditar = Intent(contexto, EditarUsuarioActivity::class.java)
                    Log.i("paso", "${usuario.nombreCompletoJugador}, ${usuario.apellido}, ${usuario.email}, ${usuario.id}")
                    intentEditar.putExtra("id_usuario", usuario.id)
                    val usuario_pasar = Usuario(usuario.id, usuario.nombreCompletoJugador, usuario.apellido, usuario.email)
                    intentEditar.putExtra("usuario_pasar",usuario_pasar)
                    // contexto.startActivity(intentEditar)
                    contexto.startActivityForResult(intentEditar, UsuariosActivity.requestCodeActualizar)
                    // contexto.finish()
//                    contexto.recreate()
                }*/


        }


    }


    // Definimos el layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.lista_equipo_rvlayout,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val persona = listaEquipos[position]

        holder.nombreTextView.setText(persona.nombre)
        holder.apellidoTextView.setText(persona.liga)
//        holder.fechaNacimientoTextView.setText(persona.fechaNacimiento)

        holder.boton_opciones.setOnClickListener {
            val popup = PopupMenu(contexto, holder.boton_opciones)
            //inflating menu from xml resource
            popup.inflate(R.menu.equipo_menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.getItemId()) {
                    R.id.editar_equipo -> {
                        JugadorHTTP().obtenerPorId(persona.id)
                        val intentEditar = Intent(contexto, FormularioEquipoActivity::class.java)
                        Log.i(
                            "paso",
                            "${persona.nombre}, ${persona.liga}, ${persona.fechaCreacion}, ${persona.id}"
                        )
//                        intentEditar.putExtra("id_usuario", persona.id)

                        val equipoActualizar = Equipo(
                            persona.id!!,
                            persona.nombre,
                            persona.liga,
                            persona.fechaCreacion,
                            persona.numeroCopasInternacionales,
                            persona.campeonActual
                        )
                        JugadorHTTP().obtenerPorId(equipoActualizar.id)

                        intentEditar.putExtra("equipo_pasar", equipoActualizar)
                        contexto.startActivityForResult(intentEditar, ListarEquipoActivity.requestCodeActualizar)
//                        contexto.startActivity(intentEditar)
//                        contexto.finish()
                        notifyDataSetChanged()
                        true
                    }
                    R.id.eliminar_equipo -> {
                        val builder = AlertDialog.Builder(contexto)
                        builder
                            .setMessage(contexto.getString(R.string.msjEliminar))
                            .setPositiveButton(
                                R.string.Si
                            ) { dialog, which ->
                                EquipoHttp().eliminar(persona.id)
                                Alerter.create(contexto)
                                    .setTitle(contexto.getString(R.string.msgEliminar)+" ${persona!!.nombre}")
                                    .setText(contexto.getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                                    .show()
                                listaEquipos.removeAt(position)
                                notifyDataSetChanged()

                            }
                            .setNegativeButton(
                                "No"
                            ) { dialog, which ->
                                Alerter.create(contexto)
                                    .setText("NO")
                                    .show()
                            }


                        val dialogo = builder.create()
                        dialogo.show()


                        true
                    }
                    R.id.compartir_equipo -> {
                        val texto = persona.nombre

                        val intent = Intent(Intent.ACTION_SEND)

                        intent.type = "text/html"

                        intent.putExtra(Intent.EXTRA_TEXT, texto)

                        contexto.startActivity(intent)
                        true
                    }
                    R.id.listar_equipos -> {
                        val intentMostrar = Intent(contexto, ListarJugadorActivity::class.java)
                        Log.i(
                            "paso",
                            "${persona.nombre}, ${persona.liga}, ${persona.fechaCreacion}, ${persona.id}"
                        )
//                        intentEditar.putExtra("id_usuario", persona.id)

                        val equipoMostrar = Equipo(
                            persona.id!!,
                            persona.nombre,
                            persona.liga,
                            persona.fechaCreacion,
                            persona.numeroCopasInternacionales,
                            persona.campeonActual
                        )

                        JugadorHTTP().obtenerPorId(equipoMostrar.id)
                        intentMostrar.putExtra("equipo_pasar", equipoMostrar)
                        contexto.startActivity(intentMostrar)

                        true
                    }
                    else -> false
                }
            }

            popup.show()
        }

    }

    override fun getItemCount(): Int {
        return listaEquipos.size
    }


}
