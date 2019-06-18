package com.example.deberrecyclerview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var join: Button
    private lateinit var username: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appID:String="APP_ID"

        CometChat.init(this,appID, object : CometChat.CallbackListener<String>() {
            override fun onSuccess(p0: String?) {
                Log.d("ASF", "Initialization completed successfully")
            }

            override fun onError(p0: CometChatException?) {
                Log.d("ASD", "Initialization failed with exception: " + p0?.message)
            }
        })
        username = findViewById(R.id.username)
        join = findViewById(R.id.join_chat)
        join.setOnClickListener {
            irARecyclerView()
            login()

        }

    }
    private fun disableAuthField() {
        join.isEnabled = false
        username.isEnabled = false
    }
    private fun login() {
        CometChat.login(username.text.toString(), getString(R.string.apiKey), object : CometChat.CallbackListener<User>() {
             override fun onSuccess(user: User) {
                username.setText("")
                enableAuthField()

                Toast.makeText(this@MainActivity, "Login Exitoso", Toast.LENGTH_SHORT).show()
            }
            override fun onError(e: CometChatException) {
                Toast.makeText(this@MainActivity, "No existe Usuario", Toast.LENGTH_SHORT).show()
                enableAuthField()
            }
        })
    }
    private fun enableAuthField() {
        join.isEnabled = true
        username.isEnabled = true
    }

    fun irARecyclerView(){
        val intentExplicito= Intent(this, Contactos::class.java)
        startActivity(intentExplicito)
    }

}