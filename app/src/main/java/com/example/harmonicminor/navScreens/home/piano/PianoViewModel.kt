package com.example.harmonicminor.navScreens.home.piano

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PianoViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _piano = mutableStateListOf<Piano>()
    val piano: List<Piano> get() = _piano


    fun readPianos() {
        viewModelScope.launch {
            try {
                val result = db.collection("pianos").get().await()
                _piano.clear()
                for (document in result) {
                    val piano = document.toObject(Piano::class.java)
                    _piano.add(piano)
                }
            } catch (e: Exception) {
                Log.e("PianoViewModel", "Error getting documents: $e")
            }
        }
    }
}