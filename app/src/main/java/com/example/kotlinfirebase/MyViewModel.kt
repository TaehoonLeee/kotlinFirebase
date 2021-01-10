package com.example.kotlinfirebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private var count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun increase() {
        count.value = count.value?.plus(1)
    }

    fun decrease() {
        count.value = count.value?.minus(1)
    }

    fun getCount() : MutableLiveData<Int>{
        return count
    }
}