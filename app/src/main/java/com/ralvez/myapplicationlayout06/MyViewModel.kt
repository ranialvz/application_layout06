package com.ralvez.myapplicationlayout06
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int> = _number

    init {
        _number.value = 0
    }

    fun loadSwitch() {
        _number.value = 1
    }
}