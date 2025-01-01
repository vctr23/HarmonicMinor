package com.example.harmonicminor.navScreens.home.dj

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DjViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _dj = mutableStateListOf<Dj>()
    val dj: List<Dj> get() = _dj


    fun readDj() {
        viewModelScope.launch {
            try {
                val result = db.collection("djs").get().await()
                _dj.clear()
                for (document in result) {
                    val dj = document.toObject(Dj::class.java)
                    _dj.add(dj)
                }
            } catch (e: Exception) {
                Log.e("DjViewModel", "Error getting documents: $e")
            }
        }
    }
}