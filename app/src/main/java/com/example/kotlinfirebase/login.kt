package com.example.kotlinfirebase

import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirebase.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnRegister.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@login, register::class.java)
                startActivity(intent)
            }
        })
    }

    private lateinit var binding: ActivityLoginBinding
}