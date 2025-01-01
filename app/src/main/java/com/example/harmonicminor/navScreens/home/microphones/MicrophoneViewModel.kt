package com.example.harmonicminor.navScreens.home.microphones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MicrophoneViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _microphone = mutableStateListOf<Microphone>()
    val microphone: List<Microphone> get() = _microphone


    fun readMicrophones() {
        viewModelScope.launch {
            try {
                val result = db.collection("microphones").get().await()
                _microphone.clear()
                for (document in result) {
                    val mic = document.toObject(Microphone::class.java)
                    _microphone.add(mic)
                }
            } catch (e: Exception) {
                Log.e("MicrophoneViewModel", "Error getting documents: $e")
            }
        }
    }
}