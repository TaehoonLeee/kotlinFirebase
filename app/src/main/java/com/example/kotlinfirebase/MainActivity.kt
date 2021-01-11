package com.example.kotlinfirebase

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirebase.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var subject = BehaviorSubject.createDefault("0")

        binding.lifecycleOwner = this
        val model: MyViewModel by viewModels()
        binding.viewModel = model

        binding.ed.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.ed.text.toString().isNotEmpty()) {
                    subject.map { binding.ed.text.toString()!!.toLong() }
                        .flatMap(
                            { BehaviorSubject.range(1, 9) },
                        { dan, gugu -> dan.toString() + " * " + gugu + " = " + dan * gugu + "\n" })
                        .scan { x, y -> x + y }
                        .subscribe { text -> binding.tv.text = text }
                }
                else {
                    subject.map { 0 }
                        .flatMap(
                            { BehaviorSubject.range(1, 9) },
                            { dan, gugu -> dan.toString() + " * " + gugu + " = " + dan * gugu + "\n" })
                        .scan { x, y -> x + y }
                        .subscribe { text -> binding.tv.text = text }
                }
            }
        })
    }

    private lateinit var binding: ActivityMainBinding
}
