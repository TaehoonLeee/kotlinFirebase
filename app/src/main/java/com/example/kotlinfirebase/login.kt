package com.example.kotlinfirebase

import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirebase.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        database = Firebase.database.getReference("users")

        binding.btnRegister.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@login, register::class.java)
                startActivity(intent)
            }
        })

        binding.btnLogin.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                login()
            }
        })
    }

    fun login() {
        var email = binding.editTextID.text.toString()
        var password = binding.editTextPWD.text.toString()

        database.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var iterator = snapshot.children.iterator()
                while(iterator.hasNext()) {
                    var childern = iterator.next()
                    Log.d("login", childern.child("email").getValue().toString())
                    Log.d("login", childern.child("password").getValue().toString())
//                    Log.d("login", snapshot.getValue().toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var database : DatabaseReference
}