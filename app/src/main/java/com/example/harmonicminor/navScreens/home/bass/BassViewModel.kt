package com.example.harmonicminor.navScreens.home.bass

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonicminor.navScreens.home.guitar.Guitar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BassViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _basses = mutableStateListOf<Guitar>()
    val basses: List<Guitar> get() = _basses


    fun readBasses() {
        viewModelScope.launch {
            try {
                val result = db.collection("basses").get().await()
                _basses.clear()
                for (document in result) {
                    val bass = document.toObject(Guitar::class.java)
                    _basses.add(bass)
                }
            } catch (e: Exception) {
                Log.e("GuitarViewModel", "Error getting documents: $e")
            }
        }
    }
}