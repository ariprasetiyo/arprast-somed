package com.arprast.sosmed.ui.media.account.youtube

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapViewModel : ViewModel(){
    private val textMap = MutableLiveData<String>().apply {
        value = "this data"
    }

    val text : LiveData<String> = textMap;
}