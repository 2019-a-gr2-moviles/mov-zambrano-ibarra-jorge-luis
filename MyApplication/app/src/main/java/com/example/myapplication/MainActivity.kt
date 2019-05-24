package com.example.dr.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irAParcelable(){
        val intentExplicito = Intent( this,
            Parcelable::class.java)
        val jorge = Usuario( "Jorge", 25, Date(),33.5)
        intentExplicito.putExtra("usuario",jorge)
        startActivity(intentExplicito)

        val cachetes = Mascota("cachetes",jorge)
        intentExplicito.putExtra("mascota",cachetes)
    }
}