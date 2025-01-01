package com.example.harmonicminor.navScreens.home.drums

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DrumsViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _drums = mutableStateListOf<Drums>()
    val drums: List<Drums> get() = _drums


    fun readDrums() {
        viewModelScope.launch {
            try {
                val result = db.collection("drums").get().await()
                _drums.clear()
                for (document in result) {
                    val drums = document.toObject(Drums::class.java)
                    _drums.add(drums)
                }
            } catch (e: Exception) {
                Log.e("DrumsViewModel", "Error getting documents: $e")
            }
        }
    }
}