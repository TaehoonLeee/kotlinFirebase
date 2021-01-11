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
                if (binding.ed.text.toString().length > 0) {
                    subject.map(object : Function<String, Long> {
                        override fun apply(t: String?): Long {
                            return binding.ed.text.toString()!!.toLong()
                        }
                    })
                        .flatMap(
                            { gugu -> Observable.range(1, 9) },
                            { dan, gugu -> dan.toString() + " * " + gugu + " = " + dan * gugu + "\n" })
                        .scan({ x, y -> x + y })
                        .subscribe({ text -> binding.tv.setText(text) })
                }
                else {
                    subject.map(object : Function<String, Long> {
                        override fun apply(t: String?): Long {
                            return 0
                        }
                    })
                        .flatMap(
                            { gugu -> Observable.range(1, 9) },
                            { dan, gugu -> dan.toString() + " * " + gugu + " = " + dan * gugu + "\n" })
                        .scan({ x, y -> x + y })
                        .subscribe({ text -> binding.tv.setText(text) })
                }
            }
        })
    }

    private lateinit var binding: ActivityMainBinding
}
