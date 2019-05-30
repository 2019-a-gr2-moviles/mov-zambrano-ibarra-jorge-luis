package com.example.parcelable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_parcelabel.setOnClickListener() {
            irAParcelable()
        }
        btn_adapter.setOnClickListener() {
            irAListView()
        }
    }
    fun irAParcelable() {
        val intentExplicito = Intent(
            this,
            Parcelable::class.java
        )
        val jorge = Usuario(
            "Jorge",
            29,
            Date(),
            12.12
        )
        intentExplicito.putExtra("usuario", jorge)

        val cachetes = Mascota("Cachetes", jorge)
        intentExplicito.putExtra("mascota", cachetes)


        startActivity(intentExplicito)
    }
    fun irAListView(){
        val intentExplicito = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }
}
