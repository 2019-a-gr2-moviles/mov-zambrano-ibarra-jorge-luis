package com.example.deberrecyclerview

import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.webrtc.ContextUtils.getApplicationContext
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK



class ContactosAdapter(
    private val listaPersonas: List<Persona>,
    private val contexto: Contactos,
    private val recyclerView: RecyclerView

): RecyclerView.Adapter<ContactosAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var imageView: ImageButton
        var accionButton: Button

        init {
            nombreTextView = view.findViewById(R.id.txt_nombre) as TextView
            imageView=view.findViewById(R.id.imageView) as ImageButton
            accionButton = view.findViewById(R.id.btn_accion) as Button
            val layout = view.findViewById(R.id.linear_layout) as LinearLayout
            layout.setOnClickListener {
                Log.i("recycler-view", "layout presionado")
            }
            accionButton.setOnClickListener {
                nombreTextView.text = "Bloqueado"
                imageView?.setImageResource(R.drawable.blocked)



            }
            imageView?.setOnClickListener(){
                val myactivity = Intent(contexto.getApplicationContext(), MessagesActivity::class.java)
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK)
                contexto.getApplicationContext().startActivity(myactivity)
            }

        }

    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }


    override fun onBindViewHolder(
        myViewHolder: ContactosAdapter.MyViewHolder,
        position: Int
    ) {

        val persona = listaPersonas[position]

        myViewHolder.nombreTextView.text = persona.nombre
        myViewHolder.imageView?.setImageResource(persona.imageId)


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            ContactosAdapter.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout,
                p0,
                false
            )

        return MyViewHolder(itemView)
    }
}
