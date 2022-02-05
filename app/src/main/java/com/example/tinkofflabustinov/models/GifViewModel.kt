package com.example.tinkofflabustinov.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinkofflabustinov.UI.GifType

class GifViewModel : ViewModel() {
    private val _numberOfSteps: List<MutableLiveData<Int>> = List(3) { MutableLiveData<Int>(0) }
    val numberOfSteps: List<LiveData<Int>>
        get() = _numberOfSteps

    fun increaseSteps(type : GifType){
        _numberOfSteps[type.ordinal].value = _numberOfSteps[type.ordinal].value?.plus(1)
    }
    fun decreaseSteps(type : GifType){
        _numberOfSteps[type.ordinal].value = _numberOfSteps[type.ordinal].value?.minus(1)
    }

}