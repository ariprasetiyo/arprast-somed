package com.arprast.sosmed.ui.media.account.instagram

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is instagram Fragment"
    }
    val text: LiveData<String> = _text
}