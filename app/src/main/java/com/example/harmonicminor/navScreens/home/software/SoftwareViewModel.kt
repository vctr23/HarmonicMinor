package com.example.harmonicminor.navScreens.home.software

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SoftwareViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _softwares = mutableStateListOf<Software>()
    val softwares: List<Software> get() = _softwares


    fun readSoftwares() {
        viewModelScope.launch {
            try {
                val result = db.collection("softwares").get().await()
                _softwares.clear()
                for (document in result) {
                    val software = document.toObject(Software::class.java)
                    _softwares.add(software)
                }
            } catch (e: Exception) {
                Log.e("SoftwareViewModel", "Error getting documents: $e")
            }
        }
    }
}