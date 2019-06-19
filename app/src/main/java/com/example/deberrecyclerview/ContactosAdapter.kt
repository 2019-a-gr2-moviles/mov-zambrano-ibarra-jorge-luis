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
import android.widget.ArrayAdapter
import android.widget.*
import org.webrtc.ContextUtils.getApplicationContext
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.widget.Toast
import android.widget.TextView






class ContactosAdapter(
    private val listaPersonas: List<Persona>,
    private val contexto: Contactos,
    private val recyclerView: RecyclerView



): RecyclerView.Adapter<ContactosAdapter.MyViewHolder>() {
    var posi:Int=0
    var estado:Boolean=false
    var estadoPos=false

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var imageView: ImageButton
        var accionButton: Button



        init {

            nombreTextView = view.findViewById(R.id.txt_nombre) as TextView
            imageView=view.findViewById(R.id.imageView) as ImageButton
            accionButton = view.findViewById(R.id.btn_accion) as Button



            imageView?.setOnClickListener(){
                if(!nombreTextView.text.toString().equals("Bloqueado")){
                    val myactivity = Intent(contexto.getApplicationContext(), MessagesActivity::class.java)
                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    contexto.getApplicationContext().startActivity(myactivity)
                }else{
                    Toast.makeText(contexto.applicationContext, "Desbloquee para chatear", Toast.LENGTH_SHORT).show()
                }
            }



        }

    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

    fun blquear(estado:Boolean){

    }
    override fun onBindViewHolder(myViewHolder: ContactosAdapter.MyViewHolder, position: Int) {

        val persona = listaPersonas[position]
        var estado1=false
        myViewHolder.nombreTextView.text = persona.nombre
        myViewHolder.imageView?.setImageResource(persona.imageId)
        myViewHolder.accionButton.setOnClickListener(){
            posi=  pos(myViewHolder.getAdapterPosition())
            Log.i("posi:",posi.toString())
            if(estado1==false) {

                myViewHolder.nombreTextView.text = "Bloqueado"
                myViewHolder.imageView?.setImageResource(R.drawable.blocked)
                estado=true
                estado1=true
                myViewHolder.accionButton.text = "Desbloquear"
            }
            else {
                val persona = listaPersonas[posi]
                myViewHolder.nombreTextView.text = persona.nombre
                myViewHolder.imageView?.setImageResource(persona.imageId)
                estado = false
                estado1=false
                myViewHolder.accionButton.text = "Bloquear"


            }

        }






    }
    fun pos(int: Int):Int{

        return int
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
