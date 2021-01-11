package com.example.kotlinfirebase

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirebase.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        database = Firebase.database.reference
        binding.btnDone.setOnClickListener { register() }
    }

    fun register() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()

        val user = User(email, password)

        database.child("users").push().setValue(user)
    }

    data class User(
        var email: String? = "",
        var password: String? = ""
    )



    private lateinit var binding : ActivityRegisterBinding
    private lateinit var database : DatabaseReference
}