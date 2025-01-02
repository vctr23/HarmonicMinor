package com.example.harmonicminor.navScreens.home.guitar

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GuitarViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _guitars = mutableStateListOf<Guitar>()
    val guitars: List<Guitar> get() = _guitars

    fun readGuitars() {
        viewModelScope.launch {
            try {
                val result = db.collection("guitars").get().await()
                _guitars.clear()
                for (document in result) {
                    val guitar = document.toObject(Guitar::class.java)
                    _guitars.add(guitar)
                }
            } catch (e: Exception) {
                Log.e("GuitarViewModel", "Error getting documents: $e")
            }
        }
    }
}