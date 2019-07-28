package com.example.examenapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView

import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.actividad_listar_materia.*


class ListarJugadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_listar_materia)
        val estudianteMostrar = intent.getParcelableExtra<Equipo?>("equipo_pasar")
        JugadorHTTP().obtenerPorId(estudianteMostrar!!.id)
        UsuarioHttp().obtenerUsuarios()
        JugadorHTTP().obtenerPorId(estudianteMostrar!!.id)

        if (estudianteMostrar != null) {

            mostrarDatos(estudianteMostrar)
        }

        for (materia in BDD.jugador) {
            Log.i("bdd-m", materia.nombreCompletoJugador)
        }

        button_crear_materia.setOnClickListener {
            irACrearMateria(estudianteMostrar)
        }

        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val rv = rview_materias

        val adaptador = MateriasAdaptador(BDD.jugador, this, estudianteMostrar)

        rview_materias.layoutManager = layoutManager
        rview_materias.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rview_materias.adapter = adaptador

        adaptador.notifyDataSetChanged()

    }

    fun mostrarDatos(equipo: Equipo) {
      textView_nombresE.setText(equipo.nombre)
        textView_semestreE.setText(equipo.liga.toString())
    }

    fun irACrearMateria(equipo: Equipo){
        val intent = Intent(this, FormularioJugadorActivity::class.java)
        intent.putExtra("equipo", equipo)
        this.startActivity(intent)
        this.finish()
    }

    override fun onBackPressed() {
        intent = Intent(this, ListarEquipoActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }

}

class MateriasAdaptador(val listaJugadors: ArrayList<JugadorHTTP>, private val contexto: ListarJugadorActivity, val equipo: Equipo) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MateriasAdaptador.MyViewHolder2>() {

    inner class MyViewHolder2(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        var nombreTextView: TextView
        var codigoTextView: TextView
        var boton_opciones: Button

        init {
            Log.i("debug", "Entro en Holder")
            nombreTextView = view.findViewById(R.id.textView_nombreMateria) as TextView
            codigoTextView = view.findViewById(R.id.textView_codigoMateria) as TextView
            boton_opciones = view.findViewById(R.id.button_opcionesM) as Button

            val layout = view.findViewById(R.id.relative_layout_materias) as RelativeLayout


        }


    }

    // Definimos el layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder2 {
        Log.i("debug", "Entro en CreateH")

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.lista_materias_rvlayout,
                parent,
                false
            )

        return MyViewHolder2(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val jugador = listaJugadors[position]
        Log.i("debug", "Entro en BindH")
        holder.nombreTextView.setText(jugador.nombreCompletoJugador)
        holder.codigoTextView.setText(jugador.numeroCamiseta.toString())
        holder.boton_opciones.setOnClickListener {
            val popup = PopupMenu(contexto, holder.boton_opciones)
            //inflating menu from xml resource
            popup.inflate(R.menu.materias_menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.getItemId()) {
                    R.id.editar_materia -> {
                        val intentEditar = Intent(contexto, FormularioJugadorActivity::class.java)
                        Log.i(
                            "paso",
                            "${jugador.nombreCompletoJugador}, ${jugador.numeroCamiseta}, ${jugador.fechaIngresoEquipo}, ${jugador.id}"
                        )
//                        intentEditar.putExtra("id_usuario", persona.id)

                        val materiaActualizar = Jugador(
                            jugador.id!!,
                            jugador.numeroCamiseta,
                            jugador.nombreCamiseta,
                            jugador.nombreCompletoJugador,
                            jugador.poderEspecialDos,
                            jugador.fechaIngresoEquipo,
                            jugador.goles,
                            jugador.latitud,
                            jugador.longitud
                        )

                        intentEditar.putExtra("materia_pasar", materiaActualizar)
                        intentEditar.putExtra("equipo", equipo)
                        //contexto.startActivityForResult(intentEditar, ListarEquipoActivity.requestCodeActualizar)
                        contexto.startActivity(intentEditar)
                        contexto.finish()
                        notifyDataSetChanged()
                        true
                    }
                    R.id.eliminar_materia -> {
                        val builder = AlertDialog.Builder(contexto)
                        builder
                            .setMessage(contexto.getString(R.string.msjEliminar))
                            .setPositiveButton(
                                R.string.Si
                            ) { dialog, which ->
                                JugadorHTTP().eliminar(jugador.id)
                                Alerter.create(contexto)

                                    .setTitle(contexto.getString(R.string.msgEliminar)+" ${jugador.nombreCompletoJugador}")
                                    .setText(contexto.getString(R.string.txtUsuario)+ " ${BDD.usuario[0].nombre}")
                                    .show()
                                listaJugadors.removeAt(position)
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
                    R.id.compartir_materia -> {
                        val texto = jugador.nombreCompletoJugador

                        val intent = Intent(Intent.ACTION_SEND)

                        intent.type = "text/html"

                        intent.putExtra(Intent.EXTRA_TEXT, texto)

                        contexto.startActivity(intent)
                        true
                    }
                    else -> false
                }
            }

            popup.show()
        }

    }
    override fun getItemCount(): Int {
        Log.i("debug", "Entro en Size")

        return listaJugadors.size
    }



}