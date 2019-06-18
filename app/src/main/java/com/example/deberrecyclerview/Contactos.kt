package com.example.deberrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contactos.*

class Contactos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactos)

        val lista= arrayListOf<Persona>()
        val recyler_view= rv_personas
        val actividad=this
        lista.add(Persona("Jorge",R.drawable.man))
        lista.add(Persona("Mario",R.drawable.girl))
        lista.add(Persona("Jacinto",R.drawable.boy))


        val adaptadorContacto=ContactosAdapter(lista, actividad, recyler_view)
        recyler_view.adapter=adaptadorContacto
        rv_personas.itemAnimator= DefaultItemAnimator()
        rv_personas.layoutManager= LinearLayoutManager(this)
        adaptadorContacto.notifyDataSetChanged()




    }
    fun iniciarRecylerView(
        lista: List<Persona>,
        actividad: Contactos,
        recycler_view: RecyclerView
    ) {
        val adaptadorPersona = ContactosAdapter(
            lista,
            actividad,
            recycler_view
        )
        recycler_view.adapter = adaptadorPersona
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.layoutManager = LinearLayoutManager(actividad)

        adaptadorPersona.notifyDataSetChanged()
    }

    fun chatear(){

    }
}
