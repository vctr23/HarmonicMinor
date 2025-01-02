package com.example.harmonicminor.navScreens.home.wind

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class WindViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _wind = mutableStateListOf<Wind>()
    val wind: List<Wind> get() = _wind


    fun readWind() {
        viewModelScope.launch {
            try {
                val result = db.collection("winds").get().await()
                _wind.clear()
                for (document in result) {
                    val wind = document.toObject(Wind::class.java)
                    _wind.add(wind)
                }
            } catch (e: Exception) {
                Log.e("WindViewModel", "Error getting documents: $e")
            }
        }
    }
}